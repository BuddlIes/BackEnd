package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.NFTRequestDto;
import com.buddle.UserManager.dto.NFTResponseDto;
import com.buddle.UserManager.dto.ResponseDataDto;
import com.buddle.UserManager.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NFTController {

    @Autowired
    NFTService nftService;

    @PostMapping("nft/check_condition")
    public Boolean checkAcquireNFT(@RequestBody NFTRequestDto reqDto){return nftService.checkAcquireNFT(reqDto);}

    @PostMapping("nft/acquireNFT")
    public Boolean acquireNFT(@RequestBody NFTRequestDto reqDto){return nftService.acquireNFT(reqDto);}

    @GetMapping("nft/get_nft")
    public Object getNFT(@RequestParam Long user_number, @RequestParam Long nft_id) {return nftService.getNFT(user_number, nft_id);}
    //GET으로 하면 body 말고 html 쪽으로 나간다.

    @GetMapping("nft/get_nft_list")
    public List<NFTResponseDto> getNFTList(@RequestParam Long user_number){return nftService.getNFTList(user_number);}

}



