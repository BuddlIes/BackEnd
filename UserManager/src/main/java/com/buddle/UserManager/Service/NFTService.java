package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.NFTRequestDto;
import com.buddle.UserManager.Dto.NFTResponseDto;
import com.buddle.UserManager.Dto.StampResponseDto;
import com.buddle.UserManager.Entity.NFTAcquireInfo;
import com.buddle.UserManager.Entity.NFTInfo;
import com.buddle.UserManager.Repository.NFTAcquireRepository;
import com.buddle.UserManager.Repository.NFTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NFTService {

    @Autowired
    NFTRepository nftRepository;

    @Autowired
    NFTAcquireRepository nftAcquireRepository;

    /*어떤 NFT를 이 유저가 획득할 수 있는지 확인함*/
    public Boolean checkAcquireNFT(NFTRequestDto nftRequestDto){

        return true;
    }

    /*어떤 NFT를 이 유저가 획득하려고 함*/
    public Boolean acquireNFT(NFTRequestDto nftRequestDto) {

        //nft 쪽 API에 지갑주소 넘기고 완료 여부 받아오기
        return true;
    }

    /*어떤 한 NFT에 대한 이 유저의 획득 정보를 조회함*/
    public NFTResponseDto getNFT(Long user_number, Long nft_id){

        NFTResponseDto nftResponseDto = new NFTResponseDto();
        return nftResponseDto;
    }

    /*이 유저가 획득한 NFT 리스트를 조회함*/
    public List<NFTResponseDto> getNFTList(Long user_number){

        List<NFTResponseDto> nftResponseDtoList = new ArrayList<>();
        return nftResponseDtoList;
    }
}
