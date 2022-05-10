package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService service;

    @Mock
    private TrelloClient client;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig config;

    @Test
    void shouldCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "Desc", "Top", "1");
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("1", "newCard", "http://card");
        when(client.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(config.getAdminMail()).thenReturn("admin@wp.pl");

        //When
        CreatedTrelloCardDto createdTrelloCardDto = service.createTrelloCard(trelloCardDto);

        //Then
        assertThat(createdTrelloCardDto).isNotNull();
        assertEquals("1", createdTrelloCardDto.getId());
    }

    @Test
    void shouldFetchEmptyTrelloBoardsList() {
        //Given
        when(client.getTrelloBoards()).thenReturn(List.of());

        //When
        List<TrelloBoardDto> trelloBoardDtos = service.fetchTrelloBoards();

        //Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void testGetTrelloBadge() {
        //Given
        TrelloTrelloDto trelloTrelloDto = new TrelloTrelloDto(1,1);
        TrelloAttachmentsDto attachmentsDto = new TrelloAttachmentsDto(trelloTrelloDto);
        TrelloBadgesDto badgesDto = new TrelloBadgesDto(12, attachmentsDto);
        when(client.getTrelloCardBadge(1L)).thenReturn(badgesDto);

        //When
        TrelloBadgesDto testBadgesDto = service.getTrelloBadge(1L);

        //Then
        assertThat(testBadgesDto).isNotNull();
        assertEquals(12, testBadgesDto.getVote());
        assertEquals(1, testBadgesDto.getAttachments().getTrello().getBoard());
        assertEquals(1, testBadgesDto.getAttachments().getTrello().getCard());

    }
}
