package com.whoisacat.showcase.view.renderer

import kotlinx.html.*
import com.whoisacat.showcase.infrastructure.dto.ResumeDto
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.stereotype.Service

@Service
class KotlinXHtmlResumeEditorRenderer: ResumeEditorRenderer {
    override fun resumeEditorPage(resume: ResumeDto) = createHTMLDocument().html {
        head {
            meta { charset = "UTF-8" }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1.0" }
            title("Редактирование резюме")
            link(rel = "stylesheet", href = "https://fonts.googleapis.com/css?family=Roboto&display=swap")
            style {
                unsafe {
                    +"""
                    body { font-family: 'Roboto', sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }
                    .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
                    h1, h2 { text-align: center; }
                    .section { margin-bottom: 20px; }
                    label { font-weight: bold; display: block; margin-top: 10px; }
                    input, textarea { 
                        width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px;
                        box-sizing: border-box; min-width: 20ch; /* минимальная ширина для полей */
                    }
                    input:focus, textarea:focus { border-color: #007BFF; outline: none; }

                    /* Контейнер для кнопок добавить/удалить */
                    .add-btn, .remove-btn { 
                        background: #28a745; color: white; border: none; padding: 5px 10px; border-radius: 4px;
                        cursor: pointer; margin-left: 5px; transition: background-color 0.3s ease;
                    }
                    .remove-btn { background: #dc3545; }
                    .remove-btn:hover, .add-btn:hover { background-color: #007BFF; }

                    /* Кнопка с крестиком */
                    .remove-btn {
                        width: 40px;
                        height: 40px;
                        padding: 0;
                        font-size: 20px;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        border-radius: 4px;
                        background-color: white;
                        border: 1px solid #ccc;
                        box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
                    }
                    .remove-btn:hover {
                        background-color: #f8d7da;
                    }

                    /* Отступы между полями контакта */
                    .flex-container {
                        display: flex;
                        gap: 15px;
                        margin-top: 5px;
                        flex-wrap: wrap;
                        align-items: center;
                    }

                    .contact-field {
                        flex: 1;
                    }

                    .contact {
                        margin-bottom: 15px;
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }

                    .contact-field input {
                        font-size: 14px;
                    }

                    /* Стиль для адаптивного дизайна */
                    @media screen and (max-width: 600px) {
                        .flex-container {
                            flex-direction: column;
                            gap: 10px;
                        }
                        .remove-btn {
                            margin-top: 10px;
                        }
                    }

                    /* Контейнеры для группы полей */
                    .group-fields {
                        display: flex;
                        gap: 15px;
                        margin-top: 5px;
                        flex-wrap: wrap;
                        align-items: center;
                    }

                    .group-fields .section {
                        flex: 1;
                    }

                    /* Группировка для форм сотрудничества и навыков */
                    .form-group {
                        display: flex;
                        flex-wrap: wrap;
                        gap: 15px;
                    }

                    /* Стиль для элементов формы, чтобы они адаптировались к экрану */
                    .form-item {
                        flex: 1 0 30%; /* Каждый элемент будет занимать 30% ширины */
                        margin-bottom: 10px;
                        min-width: 200px; /* Минимальная ширина элемента */
                        display: flex;
                        align-items: center;
                    }

                    /* Высота крестика должна быть такой же, как у текстового поля */
                    .remove-btn {
                        height: 36px; /* Высота крестика */
                        width: 36px; /* Ширина крестика */
                        font-size: 20px;
                    }

                    .form-item .remove-btn {
                        margin-left: 10px;
                        margin-top: 0;
                    }

                    /* Когда экран меньше 600px, элементы будут по 1 в строке */
                    @media screen and (max-width: 600px) {
                        .form-item {
                            flex: 1 0 100%; /* 1 элемент в строке */
                            max-width: 100%;
                            min-width: 100%; /* Минимальная ширина 100% для одного элемента */
                        }
                    }

                    /* Когда экран больше 600px, но меньше 900px (два элемента в строке) */
                    @media screen and (max-width: 900px) and (min-width: 600px) {
                        .form-item {
                            flex: 1 0 48%; /* 2 элемента в строке */
                            min-width: 200px; /* Минимальная ширина элемента */
                        }
                    }

                    /* Последний ряд с одинаковой шириной элементов */
                    .form-item:last-child,
                    .form-item:last-child ~ .form-item {
                        flex: 1 0 30%; /* Все элементы в последнем ряду будут одинаковой ширины */
                    }
                    """
                }
            }
        }
        body {
            div("container") {
                h1 { +"Редактирование резюме" }
                form {
                    id = "resumeForm"

                    div("section") {
                        label { +"Название резюме" }
                        textInput { id = "label"; value = resume.label }
                    }

                    // Фамилия, имя и дата рождения в одну строку
                    div("group-fields") {
                        div("section") {
                            label { +"Фамилия" }
                            textInput { id = "lastName"; value = resume.person.lastName }
                        }
                        div("section") {
                            label { +"Имя" }
                            textInput { id = "firstName"; value = resume.person.firstName }
                        }
                        div("section") {
                            label { +"Дата рождения" }
                            input(InputType.date) { id = "birthDate"; value = resume.person.birthDate.toString() }
                        }
                    }

                    // Город и гражданство в одну строку
                    div("group-fields") {
                        div("section") {
                            label { +"Город" }
                            textInput { id = "city"; value = resume.person.city }
                        }
                        div("section") {
                            label { +"Гражданство" }
                            textInput { id = "citizenship"; value = resume.person.citizenship }
                        }
                    }

                    // Контакты
                    div("section") {
                        label { +"Контакты" }
                        div { id = "contactsContainer" }
                        // Добавим вывод контактов
                        resume.contacts.forEach { contact ->
                            div("contact") {
                                div("flex-container") {
                                    // Поле "Название"
                                    div("contact-field") {
                                        textInput { id = "contactTitle"; value = contact.title }
                                    }
                                    // Поле "Текст"
                                    div("contact-field") {
                                        textInput { id = "contactText"; value = contact.text }
                                    }
                                    // Поле "Ссылка"
                                    div("contact-field") {
                                        textInput { id = "contactLink"; value = contact.link ?: "" }
                                    }
                                    // Кнопка для удаления
                                    button(classes = "remove-btn", type = ButtonType.button) {
                                        +"❌"
                                        attributes["onclick"] = "removeContactField(this)"
                                    }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить контакт"
                            attributes["onclick"] = "addContactField()"
                        }
                    }

                    // Формы сотрудничества
                    div("section") {
                        label { +"Формы сотрудничества" }
                        div("form-group") {
                            // Выводим формы сотрудничества
                            resume.cooperationForms.forEach { cooperation ->
                                div("form-item") {
                                    textInput { value = cooperation }
                                    // Кнопка для удаления формы
                                    button(classes = "remove-btn", type = ButtonType.button) {
                                        +"❌"
                                        attributes["onclick"] = "removeCooperationField(this)"
                                    }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить форму"
                            attributes["onclick"] = "addCooperationField()"
                        }
                    }

                    // Навыки
                    div("section") {
                        label { +"Навыки" }
                        div("form-group") {
                            // Выводим навыки
                            resume.skills.forEach { skill ->
                                div("form-item") {
                                    textInput { value = skill }
                                    // Кнопка для удаления навыка
                                    button(classes = "remove-btn", type = ButtonType.button) {
                                        +"❌"
                                        attributes["onclick"] = "removeSkillField(this)"
                                    }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить навык"
                            attributes["onclick"] = "addSkillField()"
                        }
                    }

                    div("section") {
                        label { +"Обо мне" }
                        textArea { id = "about"; rows = "4"; +resume.aboutMe }
                    }

                    button { +"Сохранить изменения"; attributes["type"] = "submit" }
                }
                div("message") { id = "message" }
            }
        }
    }.serialize()
}
