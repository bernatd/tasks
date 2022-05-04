package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {

    @Test
    void testMapToBoards() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "list3", true);
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);
        trelloListDto.add(trelloListDto3);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board 1", trelloListDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board 2", trelloListDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = mapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloList trelloList1 = new TrelloList("1", "list1", false);
        TrelloList trelloList2 = new TrelloList("2", "list2", true);
        TrelloList trelloList3 = new TrelloList("3", "list3", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board 1", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board 2", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardDtos = mapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2, trelloBoardDtos.size());
    }

    @Test
    void testMapToList() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "list3", true);
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);
        trelloListDto.add(trelloListDto3);
        //When
        List<TrelloList> trelloLists = mapper.mapToList(trelloListDto);
        //Then
        assertEquals(3, trelloLists.size());
    }

    @Test
    void testMapToListDto() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloList trelloList1 = new TrelloList("1", "list1", false);
        TrelloList trelloList2 = new TrelloList("2", "list2", true);
        TrelloList trelloList3 = new TrelloList("3", "list3", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        //When
        List<TrelloListDto> trelloListDto = mapper.mapToListDto(trelloLists);
        //Then
        assertEquals(3, trelloListDto.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card1", "Card 1", "top", "2");
        TrelloMapper mapper = new TrelloMapper();
        //When
        TrelloCardDto trelloCardDto = mapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Card1", trelloCardDto.getName());
        assertEquals("Card 1", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("2", trelloCardDto.getListId());
    }

    @Test
    void testMapTpCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card2", "Card 2", "bottom", "3");
        TrelloMapper mapper = new TrelloMapper();
        //When
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Card2", trelloCardDto.getName());
        assertEquals("Card 2", trelloCardDto.getDescription());
        assertEquals("bottom", trelloCardDto.getPos());
        assertEquals("3", trelloCardDto.getListId());
    }
}
