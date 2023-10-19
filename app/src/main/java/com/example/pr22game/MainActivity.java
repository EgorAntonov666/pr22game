package com.example.pr22game;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private int[] cardArray = {
            R.drawable.goblin, R.drawable.robber,
            R.drawable.robber, R.drawable.robber,
            R.drawable.robber, R.drawable.robber,
            R.drawable.robber, R.drawable.robber,
            R.drawable.dragon_head, R.drawable.temptation,
            R.drawable.suspicious, R.drawable.surprised,
            R.drawable.surprised_skull, R.drawable.tentacles_skull,
            R.drawable.tentacurl, R.drawable.thorny_tentacle,
            R.drawable.thorny_vine, R.drawable.vine_whip
    };
    private ImageButton[] cards;
    private int[] cardValues;
    private int card1, card2;
    private int clickedCard1, clickedCard2;
    private int cardCount;
    private TextView scoreTextView; // Для отображения счета
    private Button restartButton; // Для кнопки "Начать заново"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        int totalCards = gridLayout.getRowCount() * gridLayout.getColumnCount();
        cards = new ImageButton[totalCards];
        cardValues = new int[totalCards];

        // Инициализируйте элементы TextView и Button
        scoreTextView = findViewById(R.id.scoreTextView);
        restartButton = findViewById(R.id.restartButton);

        initializeCards(totalCards);
        shuffleCards();
    }

    // Добавьте обработчик для кнопки "Начать заново"
    public void onRestartClick(View view) {
        // Сбросить счет
        score = 0;
        scoreTextView.setText("Score: " + score);

        // Перетасовать карты
        shuffleCards();

        // Восстановить все карточки
        for (int i = 0; i < cardValues.length; i++) {
            cardValues[i] = 0;
            cards[i].setImageResource(R.drawable.card_bg);
            cards[i].setVisibility(View.VISIBLE);
        }
    }



    public void onCardClick(View view) {
        int cardIndex = Integer.parseInt(view.getTag().toString());

        if (cardValues[cardIndex] == 0 && cardCount < 2) {
            cardCount++;
            cards[cardIndex].setImageResource(cardArray[cardIndex]);
            cardValues[cardIndex] = cardArray[cardIndex];

            if (cardCount == 1) {
                card1 = cardValues[cardIndex];
                clickedCard1 = cardIndex;
            } else if (cardCount == 2) {
                card2 = cardValues[cardIndex];
                clickedCard2 = cardIndex;

                if (card1 == card2) {
                    // Cards match
                    cards[clickedCard1].setVisibility(View.INVISIBLE);
                    cards[clickedCard2].setVisibility(View.INVISIBLE);
                } else {
                    // Cards don't match
                    cards[clickedCard1].setImageResource(R.drawable.card_bg);
                    cards[clickedCard2].setImageResource(R.drawable.card_bg);
                }
                resetCardCount();
            }
        }

    }

    private void resetCardCount() {
        cardCount = 0;
        card1 = 0;
        card2 = 0;
    }

    private void initializeCards(int totalCards) {
        for (int i = 0; i < totalCards; i++) {
            cards[i] = findViewById(getResources().getIdentifier("card" + (i + 1), "id", getPackageName()));
            if (cards[i] != null) {
                cards[i].setTag(i);
            }
        }
    }

    private void shuffleCards() {
        Random random = new Random();

        for (int i = cardArray.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1); // Generate a random index within bounds
            int temp = cardArray[randomIndex];
            cardArray[randomIndex] = cardArray[i];
            cardArray[i] = temp;
        }
    }


}

