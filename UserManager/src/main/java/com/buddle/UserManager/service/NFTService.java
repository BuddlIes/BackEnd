package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.entity.*;
import com.buddle.UserManager.repository.*;
import com.buddle.UserManager.security.TokenProvider;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NFTService {

    @Autowired
    NFTRepository nftRepository;

    @Autowired
    NFTAcquireRepository nftAcquireRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StampAcquireRepository stampAcquireRepository;

    @Autowired
    private TokenProvider tokenProvider;

    /*어떤 NFT를 이 유저가 획득할 수 있는지 확인함*/
    public Boolean checkAcquireNFT(NFTRequestDto nftRequestDto){


        //NFT와 유저가 존재하는지 확인
        Optional<NFTInfo> optNFTInfo = nftRepository.findById(nftRequestDto.getNft_id());
        if(optNFTInfo.isEmpty()){return false;}

        Optional<UserInfo> optUserInfo = userRepository.findById(nftRequestDto.getUser_number());
        if(optUserInfo.isEmpty()){return false;}

        //이 유저가 쓸 수 있는 스탬프의 수 가져오기
        Long stamp_usable = 10000L;
        stamp_usable = stampAcquireRepository.countDistinctByUserNumberEquals(nftRequestDto.getUser_number());
        List<Object[]> his_nfts = nftRepository.findNFTInfoListWithAcquire(nftRequestDto.getUser_number());
        for(Object[] one : his_nfts) {
            stamp_usable = stamp_usable - ((NFTInfo)one[0]).getNeeded_stamp();
        }

        //획득 조건을 비교하기
        if(optNFTInfo.get().getNeeded_stamp() <= stamp_usable){

            //스탬프 개수가 넉넉하므로 조건 만족
            return true;

        }else{
            //스탬프 개수가 모자람
            return false;

        }
    }

    /*어떤 NFT를 이 유저가 획득하려고 함*/
    public Object acquireNFT(NFTRequestDto nftRequestDto) {

        //nft 획득 가능 여부 조사하기
        if( !checkAcquireNFT(nftRequestDto) ) return false;

        //nft 쪽 API에 지갑주소 넘기고 완료 여부 받아오기
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        String url = "<https://52.79.132.18:8080/nft?wallet="+nftRequestDto.getWallet()+"&n="+nftRequestDto.getNft_id().toString()+">";
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();


        //nft DB에 회득 정보 저장하기
//        NFTAcquireInfo nftAcquireInfo = new NFTAcquireInfo();
//        nftAcquireInfo.setNftId(nftRequestDto.getNft_id());
//        nftAcquireInfo.setUserNumber(nftRequestDto.getUser_number());
//        nftAcquireInfo.setAcquire_time(LocalDateTime.now());
//
//        nftAcquireRepository.save(nftAcquireInfo);
//
//        return true;
    }

    /*어떤 한 NFT에 대한 이 유저의 획득 정보를 조회함*/
    public Object getNFT(Long user_number, Long nft_id){

        Optional<Object[]> nftInfoWithAcquire = nftRepository.findNFTInfoWithAcquire(user_number, nft_id);

        NFTResponseDto nftResponseDto = new NFTResponseDto();

        //비어있으면 빈 것 리턴
        if( nftInfoWithAcquire.isEmpty() ){
            return nftResponseDto;
        }



//        //안 비어있다면 안 빈 거 리턴
//        nftResponseDto.setNft_id( ((NFTInfo)inner[0]).getNft_id());
//        nftResponseDto.setNft_name( ((NFTInfo)inner[0]).getNft_name());
//        nftResponseDto.setNft_category( ((NFTInfo)inner[0]).getNft_category());
//        nftResponseDto.setDiscount_rate( ((NFTInfo)inner[0]).getDiscount_rate());
//        nftResponseDto.setNeeded_stamp( ((NFTInfo)inner[0]).getNeeded_stamp());
//
//        nftResponseDto.setAcquire_id( ((NFTAcquireInfo)inner[1]).getAcquire_id());
//        nftResponseDto.setAcquire_time( ((NFTAcquireInfo)inner[1]).getAcquire_time());

        return nftResponseDto;
    }

    /*이 유저가 획득한 NFT 리스트를 조회함*/
    public List<NFTResponseDto> getNFTList(Long user_number){

        List<NFTResponseDto> nftResponseDtoList = new ArrayList<>();
        List<Object[]> nftInfoListWithAcquire = nftRepository.findNFTInfoListWithAcquire(user_number);

        for (Object[] one : nftInfoListWithAcquire) {

            NFTResponseDto nftResponseDto = new NFTResponseDto();
            nftResponseDto.setNft_id( ((NFTInfo)one[0]).getNft_id() );
            nftResponseDto.setNft_name( ((NFTInfo)one[0]).getNft_name() );
            nftResponseDto.setNft_category( ((NFTInfo)one[0]).getNft_category() );
            nftResponseDto.setDiscount_rate( ((NFTInfo)one[0]).getDiscount_rate() );
            nftResponseDto.setNeeded_stamp( ((NFTInfo)one[0]).getNeeded_stamp() );

            nftResponseDto.setAcquire_id( ((NFTAcquireInfo)one[1]).getAcquire_id() );
            nftResponseDto.setAcquire_time( ((NFTAcquireInfo)one[1]).getAcquire_time() );

            nftResponseDtoList.add(nftResponseDto);
        }

        return nftResponseDtoList;
    }
}
