# qa_java Sprint_6
Запустить тест в командной строке:

Для Chrome:
`mvn clean test -Dbrowser=chrome -e`

Для Firefox:
`mvn clean test -Dbrowser=firefox -e`

По умолчанию тесты запускаются через Firefox. Параметров очень много при запуске тестов. 

`MainPage` - описывается страницу главной страницы сайта.  
`OrderPage` - описывается страница заказа самоката.

[TestCheckTextInAccordionPanelPassed.java](src/test/java/ru/praktikum_services/qa_scooter/TestCheckTextInAccordionPanelPassed.java) - Тест проверяющий текст на главной странице.
[TestOrderScooterTopButtonPassed.java](src/test/java/ru/praktikum_services/qa_scooter/TestOrderScooterTopButtonPassed.java) - тест заказа самоката.

При прогоне тестов через chrome - выявлена ошибки:
1. Не нажимается кнопка "ДА" при подтверждении заказа.
2. Не поддерживается латиница в полях ввода Имени и Фамилии.

Недочеты в работе: 
1. Не удалось подключить менеджер селениума. Из-за внутреннего конфликта IDE (Старая версия, обновиться не могу - рабочий ПК)
2. Нет доп. задания. Работа сдается в минимальных требованиях. 