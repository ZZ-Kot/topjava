package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ru.javawebinar.topjava.service.MealService;

/*
 * 4: Реализовать слои приложения для функциональности "еда".
 * API контроллера должна удовлетворять все потребности демо
 * приложения и ничего лишнего (см. демо).
 * */
/*
 * 4.1: после авторизации (сделаем позднее), id авторизованного
 * юзера можно получить из SecurityUtil.authUserId(). Запрос попадает
 * в контроллер, методы которого будут доступны снаружи по http, т.е.
 * запрос можно будет сделать с ЛЮБЫМ id для еды (не принадлежащем
 * авторизированному пользователю). Нельзя позволять модифицировать/смотреть чужую еду.
 * */
/*
 * Смотрите на реализацию слоя для user и делаете по аналогии!
 * Если там что-то непонятно, не надо исправлять или делать по своему.
 * Задавайте вопросы. Если действительно нужна правка- я сделаю и напишу всем.
 * */
/*
 * 4.6 еще раз: не надо в названиях методов повторять названия класса (Meal).
 * */
/*
 * 5: включить классы еды в контекст Spring (добавить аннотации) и
 * вызвать из SpringMain любой метод MealRestController (проверить
 * что Spring все корректно заинжектил)
 * */
@Controller
public class MealRestController extends AbstractMealController {
}