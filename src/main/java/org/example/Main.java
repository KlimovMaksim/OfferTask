package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        startLogic();
    }

    private static void startLogic(){
        ObjectMapper objectMapper = new ObjectMapper();
        Offer[] offers; // массив сервисов подписчиков
        Message message; // Message запрос из json
        boolean hasTrigger = false; // было ли триггер поле изменено или нет
        int[] inputValues = new int[2]; // набор входных данных из первой строки
        int servicesCount; // кол-во сервисов подписчиков
        int requestCount;  // кол-во запросов на обновление
        int triggerCount; // кол-во trigger полей
        int shipmentCount; // кол-во shipment полей
        String event; // каждая прочитанная строка из файла
        String[] eventSplit; // разделенная через пробел строка из файла
        File file = new File("src/main/resources/input.txt");

        try (Scanner scanner = new Scanner(file)){
            // считываем данные из первой строки и заносим в переменные servicesCount, requestCount
            event = scanner.nextLine();
            eventSplit = event.split(" ");
            for (int i = 0; i < 2; i++) {
                inputValues[i] = Integer.parseInt(eventSplit[i]);
            }
            servicesCount = inputValues[0];
            requestCount = inputValues[1];

            // объявляем размерность массива offers
            offers = new Offer[servicesCount];

            // считываем данные из следующих servicesCount строк, заполняем массив offers экземплярами класса Offer,
            // для каждого экзепляра считываем trigger, shipment поля и вносим их в triggerSet, shipmentSet
            for (int i = 0; i < servicesCount; i++){
                event = scanner.nextLine();
                eventSplit = event.split(" ");
                triggerCount = Integer.parseInt(eventSplit[0]);
                shipmentCount = Integer.parseInt(eventSplit[1]);
                offers[i] = new Offer(Integer.toString(i + 1), triggerCount, shipmentCount);

                // добавляем trigger поля в triggerSet
                for (int j = 0; j < triggerCount; j++){
                    offers[i].triggerSet.add(eventSplit[j + 2]);
                }
                // добавляем shipment поля в shipmentSet
                for (int j = 0; j < shipmentCount; j++){
                    offers[i].shipmentSet.add(eventSplit[j + triggerCount + 2]);
                }
            }

            // считываем данные json запросов из следующих requestCount строк
            for (int i = 0; i < requestCount; i++){
                event = scanner.nextLine();
                message = objectMapper.readValue(event, Message.class);
                if (offers[Integer.parseInt(message.getOffer().getId()) - 1].priceUpdate(message.getOffer().getPrice())){
                    if (offers[Integer.parseInt(message.getOffer().getId()) - 1].triggerSet.contains("price")) {
                        hasTrigger = true;
                    }
                }
                if (offers[Integer.parseInt(message.getOffer().getId()) - 1].stock_countUpdate(message.getOffer().getStock_count())) {
                    if (offers[Integer.parseInt(message.getOffer().getId()) - 1].triggerSet.contains("stock_count")) {
                        hasTrigger = true;
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}