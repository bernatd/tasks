package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {
    private final static String SUBJECT = "Tasks: New Trello card";
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card -> emailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("New card: " + trelloCardDto.getName() + " has been created on your Trello account")
                        .build()
        ));
        return newCard;
    }

    public TrelloBadgesDto getTrelloBadge(Long id) {
        return trelloClient.getTrelloCardBadge(id);
    }
}
