package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testValidateTrelloBoards() {
        //Given
        TrelloValidator validator = new TrelloValidator();
        List<TrelloList> trelloLists1 = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();
        TrelloList list1 = new TrelloList("1", "test", false);
        TrelloList list2 = new TrelloList("2", "List_2", false);
        trelloLists1.add(list1);
        trelloLists2.add(list2);
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test", trelloLists1),
                new TrelloBoard("2", "Board1", trelloLists2));

        //When
        List<TrelloBoard> resultList = validator.validateTrelloBoards(trelloBoards);

        //Then
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(1);
        assertThat(resultList.get(0).getName()).isEqualTo("Board1");
    }
}
