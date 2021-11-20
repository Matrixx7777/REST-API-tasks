package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    @Autowired
    private final TrelloFacade trelloFacade;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }

//    private final TrelloService trelloService;
//
//    @GetMapping("getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards(){
//        return  trelloService.fetchTrelloBoards();
//    }
//
//    @PostMapping("createTrelloCard")
//    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloService.createTrelloCard(trelloCardDto);
//    }

//        private final TrelloClient trelloClient;

//    @GetMapping("getTrelloBoards")
//    public void getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList -> {
//                System.out.println(
//                        trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()
//                );
//            });
//        });
//    }

//    @GetMapping("getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards(){
//        return  trelloClient.getTrelloBoards();
//    }
//
//    @PostMapping("createTrelloCard")
//    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloClient.createNewCard(trelloCardDto);
//    }
}