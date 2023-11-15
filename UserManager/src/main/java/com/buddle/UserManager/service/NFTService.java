package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.entity.*;
import com.buddle.UserManager.repository.*;
import com.buddle.UserManager.security.TokenProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
        Long stamp_usable = 0L;
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
    private Object nftServerGet(NFTRequestDto nftRequestDto) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
        factory.setReadTimeout(5000); // 타임아웃 설정 5초

        //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
//            HttpClient httpClient = HttpClientBuilder.create()
//                    .setMaxConnTotal(50)//최대 커넥션 수
//                    .setMaxConnPerRoute(20).build();
//            factory.setHttpClient(httpClient);

        //2. RestTemplate 객체를 생성합니다.
        RestTemplate restTemplate = new RestTemplate(factory);

        // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // 4. 요청 URL을 정의해줍니다.
        String url = "http://52.79.132.18:8080/nft?wallet='" + nftRequestDto.getWallet() + "'&n=" + nftRequestDto.getNft_id().toString();
        //String url = "http://52.79.132.18:8080/nft?wallet='0x721F63C3c0677C0FBfffA411646041F72f34efB1'&n=1";

        // 5. exchange() 메소드로 api를 호출합니다.
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

        // 6. 요청한 결과를 HashMap에 추가합니다.
        return response.getBody();

    }

    /*어떤 NFT를 이 유저가 획득하려고 함*/
    public Object acquireNFT(NFTRequestDto nftRequestDto) {

        //nft 획득 가능 여부 조사하기
        if( !checkAcquireNFT(nftRequestDto) ) return false;

        //nft 쪽 API에 지갑주소 넘기고 완료 여부 받아오기
        Object response = nftServerGet(nftRequestDto);

        return response;


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

        Optional<NFTInfo> nftInfo = nftRepository.findById(nft_id);
        Optional<NFTAcquireInfo> nftAcquireInfo = nftAcquireRepository.findByUserNumberAndNftId(user_number, nft_id);

        NFTResponseDto nftResponseDto = new NFTResponseDto();

        //만약 nft가 존재하지 않거나, 유저가 획득하지 않았다면 빈 dto를 리턴
        if(nftInfo.isEmpty()) return nftResponseDto;
        if(nftAcquireInfo.isEmpty()) return nftResponseDto;

        //안 비어있다면 안 빈 거 리턴
        nftResponseDto.setNft_id(nftInfo.get().getNft_id());
        nftResponseDto.setNft_name(nftInfo.get().getNft_name());
        nftResponseDto.setNft_category(nftInfo.get().getNft_category());
        nftResponseDto.setDiscount_rate(nftInfo.get().getDiscount_rate());
        nftResponseDto.setNeeded_stamp(nftInfo.get().getNeeded_stamp());

        nftResponseDto.setAcquire_id(nftAcquireInfo.get().getAcquire_id());
        nftResponseDto.setAcquire_time(nftAcquireInfo.get().getAcquire_time());

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
