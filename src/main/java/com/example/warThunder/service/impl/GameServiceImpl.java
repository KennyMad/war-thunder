package com.example.warThunder.service.impl;

import com.example.warThunder.exception.NotUniqueUsername;
import com.example.warThunder.service.GameService;
import com.example.warThunder.service.dto.GameDto;
import com.example.warThunder.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {
    @Override
    public GameDto createGame(UserDto player1, UserDto player2){
        int[][] botField = new int[10][10];
        ships(botField);

        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                //ну и здесь, если была консолька можно было принтануть массив
               
            }
        }
    }

    private boolean freedom(int x, int y, int[][] Pole) {

        int dx, dy;

        if ((x >= 0) & (x < 10) & (y >= 0) & (y < 10) && ((Pole[x][y] == 0) || (Pole[x][y] == 2))) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    dx = x + i;
                    dy = y + j;
                    if ((dx >= 0) & (dx < 10) & (dy >= 0) & (dy < 10) && (Pole[dx][dy] == 1)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void ships(int[][] Pole) {
        int x, y, kx, ky;
        boolean B;
        Random random = new Random();

        for (int N = 3; N >= 0; N--) {
            for (int M = 0; M <= 3 - N; M++) {
                do {
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                    kx = random.nextInt(2);
                    if (kx == 0) {
                        ky = 1;
                    } else {
                        ky = 0;
                    }
                    B = true;
                    for (int j = 0; j <= N; j++) {
                        if (!(freedom(x + kx * j, y + ky * j, Pole))) {
                            B = false;
                            break;
                        }
                    }
                    if (B) {
                        for (int k = 0; k <= N; k++) {
                            Pole[x + kx * k][y + ky * k] = 1;
                        }
                    }
                }
                while (!(B));
            }
        }

    }
}
