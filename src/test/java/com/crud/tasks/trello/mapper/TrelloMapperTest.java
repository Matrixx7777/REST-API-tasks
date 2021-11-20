package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("10", "Test", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        TrelloBoardDto trelloDto1= new TrelloBoardDto("1","Test-MapToBoards",trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtoLists = new ArrayList<>();
        trelloBoardDtoLists.add(trelloDto1);

        List<TrelloBoard> mapToBoards = trelloMapper.mapToBoards(trelloBoardDtoLists);

        //When
        String getId = mapToBoards.get(0).getId();
        String getName = mapToBoards.get(0).getName();
        List<TrelloList> trelloList = mapToBoards.get(0).getLists();

        //Then
        assertEquals(getId,"1");
        assertEquals(getName,"Test-MapToBoards");
        assertEquals(trelloList.size(),1);
        assertThat(trelloList).isNotNull();
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        TrelloList trelloList = new TrelloList("10", "Test", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trello1= new TrelloBoard("1","Test-MapToBoardsDto",trelloLists);
        List<TrelloBoard> trelloBoardLists = new ArrayList<>();
        trelloBoardLists.add(trello1);

        List<TrelloBoardDto> mapToBoardsDto = trelloMapper.mapToBoardsDto(trelloBoardLists);

        //When
        String getId = mapToBoardsDto.get(0).getId();
        String getName = mapToBoardsDto.get(0).getName();
        List<TrelloListDto> trelloListDtos = mapToBoardsDto.get(0).getLists();

        //Then
        assertEquals(getId,"1");
        assertEquals(getName,"Test-MapToBoardsDto");
        assertEquals(trelloListDtos.size(),1);
        assertThat(trelloListDtos).isNotNull();
    }

    @Test
    public void testMapToList(){
        //Given
        List<TrelloListDto> trelloListDto = List.of(new TrelloListDto("1","Test-MapToList",false));

        //When
        String getId = trelloListDto.get(0).getId();
        String getName = trelloListDto.get(0).getName();
        List<TrelloList> mapToList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(getId,"1");
        assertEquals(getName,"Test-MapToList");
        assertEquals(mapToList.size(),1);
        assertThat(mapToList).isNotNull();
    }

    @Test
    public void testMapToListDto(){
        //Given
        List<TrelloList> trelloList = List.of(new TrelloList("1","Test-MapToListDto",false));

        //When
        String getId = trelloList.get(0).getId();
        String getName = trelloList.get(0).getName();
        List<TrelloListDto> mapToListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(getId,"1");
        assertEquals(getName,"Test-MapToListDto");
        assertEquals(mapToListDto.size(),1);
        assertThat(mapToListDto).isNotNull();
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard =
                new TrelloCard("Test-MapToCardDto","Test-Desc","Test-Pos","1");

        //When
        String getName = trelloCard.getName();
        String getDesc = trelloCard.getDescription();
        String getPos = trelloCard.getPos();
        String getListId = trelloCard.getListId();
        TrelloCardDto mapToCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(getName,"Test-MapToCardDto");
        assertEquals(getDesc,"Test-Desc");
        assertEquals(getPos,"Test-Pos");
        assertEquals(getListId,"1");
        assertThat(mapToCardDto).isNotNull();
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto =
                new TrelloCardDto("Test-MapToCard","Test-Desc","Test-Pos","1");

        //When
        String getName = trelloCardDto.getName();
        String getDesc = trelloCardDto.getDescription();
        String getPos = trelloCardDto.getPos();
        String getListId = trelloCardDto.getListId();
        TrelloCard mapToCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(getName,"Test-MapToCard");
        assertEquals(getDesc,"Test-Desc");
        assertEquals(getPos,"Test-Pos");
        assertEquals(getListId,"1");
        assertThat(mapToCard).isNotNull();
    }
}