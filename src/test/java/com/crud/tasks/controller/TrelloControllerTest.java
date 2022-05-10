package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloControllerTest {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade facade;

    @Test
    void shouldFetchEmptyBoardsList() {
        //Given
        when(facade.fetchTrelloBoards()).thenReturn(List.of());

        //When
        ResponseEntity<List<TrelloBoardDto>> boardsList = trelloController.getTrelloBoards();

        //Then
        assertThat(boardsList).isNotNull();
        assertThat(boardsList.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(boardsList.getBody().size()).isEqualTo(0);
    }

    @Test
    void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("test", "desc", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test", "http://card");
        when(facade.createCard(cardDto)).thenReturn(createdTrelloCardDto);

        //When
        ResponseEntity<CreatedTrelloCardDto> trelloCard = trelloController.createdTrelloCard(cardDto);

        //Then
        assertThat(trelloCard).isNotNull();
        assertThat(trelloCard.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(trelloCard.getBody().getId()).isEqualTo("1");
        assertThat(trelloCard.getBody().getName()).isEqualTo("test");
        assertThat(trelloCard.getBody().getShortUrl()).isEqualTo("http://card");
    }

    @Test
    void shouldGetTrelloBadges() {
        //Given
        TrelloTrelloDto trello = new TrelloTrelloDto(2,5);
        TrelloAttachmentsDto attachments = new TrelloAttachmentsDto(trello);
        TrelloBadgesDto badges = new TrelloBadgesDto(12, attachments);
        when(facade.getTrelloBadges(1L)).thenReturn(badges);

        //When
        ResponseEntity<TrelloBadgesDto> badgesDto = trelloController.getTrelloBadges(1L);

        //Then
        assertThat(badgesDto).isNotNull();
        assertThat(badgesDto.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
