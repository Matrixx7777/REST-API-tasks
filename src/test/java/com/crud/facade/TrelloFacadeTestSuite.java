package com.crud.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void testFacade(){
        //Given
        List<TrelloListDto> trelloListDtos = List.of(new TrelloListDto("1","Test-TrelloListDto", false));
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("1", "Test-TrelloBoardDto", trelloListDtos));
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "Test-TrelloList", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "Test-TrelloBoard", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardsDto(trelloBoards)).thenReturn(trelloBoardDtos);
//        when(trelloMapper.mapToList(trelloListDtos)).thenReturn(trelloLists);
//        when(trelloMapper.mapToListDto(trelloLists)).thenReturn(trelloListDtos);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloFacadeBoards = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(trelloFacadeBoards).isNotNull();
        assertEquals(trelloFacadeBoards.size(), 0);
    }
}
