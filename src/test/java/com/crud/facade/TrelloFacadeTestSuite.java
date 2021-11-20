package com.crud.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloFacadeTestSuite {

    public TrelloFacade trelloFacade;

    public TrelloService trelloService;

    public TrelloMapper trelloMapper;

    public TrelloValidator trelloValidator;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos =
                List.of(new TrelloListDto("1", "Test-TrelloListDto", false));
        List<TrelloBoardDto> trelloBoardDtos =
                List.of(new TrelloBoardDto("1", "Test-TrelloBoardDto", trelloListDtos));

        //When
        List<TrelloBoardDto> service = trelloService.fetchTrelloBoards();
        List<TrelloBoard> mapToBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        trelloValidator.validateTrelloBoards(mapToBoards);
        List<TrelloBoardDto> trelloFacadeBoards = trelloFacade.fetchTrelloBoards();

        //Then
        assertThat(service).isNotNull();
        assertThat(trelloFacadeBoards).isNotNull();
        assertEquals(trelloFacadeBoards.size(), 0);
    }

    @Test
    public void testCreateCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test","Test","Test","1");

        //When
        CreatedTrelloCard createThisCard = trelloService.createTrelloCard(trelloCardDto);
        TrelloCard mapToCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(mapToCard);
        CreatedTrelloCard createCard = trelloFacade.createCard(trelloCardDto);

        //Then
        assertThat(createThisCard).isNotNull();
        assertThat(createCard).isNotNull();
        assertEquals(createCard.getId(), "1");
        assertEquals(createCard.getName(), "Test");
    }
}