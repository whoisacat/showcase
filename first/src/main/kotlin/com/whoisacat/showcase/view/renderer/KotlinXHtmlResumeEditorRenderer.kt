package com.whoisacat.showcase.view.renderer

import kotlinx.html.*
import com.whoisacat.showcase.infrastructure.dto.ResumeCDto
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.stereotype.Service

@Service
class KotlinXHtmlResumeEditorRenderer: ResumeEditorRenderer {
    override fun resumeEditorPage(resume: ResumeCDto) = createHTMLDocument().html {
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
                        box-sizing: border-box; 
                    }
                    input:focus, textarea:focus { border-color: #007BFF; outline: none; }
                    .add-btn { 
                        background: #28a745; color: white; border: none; padding: 5px 10px; border-radius: 4px;
                        cursor: pointer; margin-left: 5px; transition: background-color 0.3s ease;
                    }
                    .add-btn:hover { background-color: #007BFF; }
                    .add-btn:active { 
                        background-color: #218838; 
                        transform: scale(0.98);
                    }
                    .remove-btn {
                        background-color: #f8d7da;
                        color: #721c24;
                        padding: 5px 10px;
                        font-size: 16px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        display: inline-flex;
                        align-items: center;
                        justify-content: center;
                        transition: background-color 0.3s ease;
                        margin-left: 10px;
                    }
                    .remove-btn:hover {
                        background-color: #f5c6cb;
                    }
                    .remove-btn:active {
                        background-color: #f1b0b7;
                        transform: scale(0.98);
                    }
                    .flex-container {
                        display: inline-flex;
                        align-items: center;
                        gap: 10px;
                    }
                    .contact-field input {
                        font-size: 14px;
                    }
                    .contact {
                        margin-bottom: 15px;
                        display: inline-flex;
                        align-items: center;
                    }
                    @media screen and (max-width: 600px) {
                        .flex-container {
                            flex-direction: column;
                            gap: 10px;
                        }
                        .remove-btn {
                            margin-top: 10px;
                        }
                    }
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
                    @media screen and (max-width: 900px) and (min-width: 600px) {
                        .form-item, .contact-field {
                            flex: 1 0 calc(50% - 15px);
                            min-width: calc(50% - 15px);
                        }
                    }
                    @media screen and (max-width: 600px) {
                        .form-item, .contact-field {
                            flex: 1 0 100%;
                            max-width: 100%;
                            min-width: 100%;
                        }
                    }
                    .experience-entry {
                        border: 1px solid #ccc;
                        border-radius: 8px;
                        padding: 15px;
                        margin-bottom: 15px;
                        position: relative;
                        background: #fff;
                        box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
                        transition: background-color 0.3s ease;
                    }
                    .experience-entry:hover {
                        background-color: #f1f1f1;
                    }
                    .remove-experience-btn {
                        position: absolute;
                        top: 10px;
                        right: 10px;
                        color: white;
                        background-color: #f8d7da;
                        border: none;
                        padding: 5px 10px;
                        border-radius: 4px;
                        cursor: pointer;
                        transition: background-color 0.3s ease, transform 0.1s ease;
                    }
                    .remove-experience-btn:hover {
                        background-color: #f5c6cb;
                    }
                    .remove-experience-btn:active {
                        background-color: #f1b0b7;
                        transform: scale(0.98);
                    }
                    .skills-container, .achievements-container, .technologies-container {
                        display: flex;
                        flex-wrap: wrap;
                        gap: 15px;
                    }
                    .skills-container .form-item, 
                    .achievements-container .form-item,
                    .technologies-container .form-item {
                        flex: 1 0 calc(33% - 10px);
                        min-width: calc(33% - 10px);
                    }
                    @media screen and (max-width: 900px) {
                        .skills-container .form-item, 
                        .achievements-container .form-item,
                        .technologies-container .form-item {
                            flex: 1 0 calc(50% - 10px);
                            min-width: calc(50% - 10px);
                        }
                    }
                    @media screen and (max-width: 600px) {
                        .skills-container .form-item, 
                        .achievements-container .form-item,
                        .technologies-container .form-item {
                            flex: 1 0 100%;
                            max-width: 100%;
                            min-width: 100%;
                        }
                    }
                    """
                }
            }
            script {
                unsafe {
                    +"""
                    function addContactField() {
                        var container = document.getElementById("contactsContainer");
                        var newContactField = document.createElement("div");
                        newContactField.className = "contact";
                        newContactField.innerHTML = `
                            <div class="flex-container">
                                <div class="contact-field">
                                    <input type="text" placeholder="Название контакта" />
                                </div>
                                <div class="contact-field">
                                    <input type="text" placeholder="Контактная информация" />
                                </div>
                                <div class="contact-field">
                                    <input type="text" placeholder="Ссылка" />
                                </div>
                                <button class="remove-btn" type="button" onclick="removeContactField(this)">❌</button>
                            </div>`;
                        container.appendChild(newContactField);
                    }
                    function addSkillField() {
                        var container = document.querySelector(".skills-container");
                        var newSkillField = document.createElement("div");
                        newSkillField.className = "form-item";
                        newSkillField.innerHTML = `
                            <div class="flex-container">
                                <input type="text" placeholder="Навыки" />
                                <button class="remove-btn" type="button" onclick="removeSkillField(this)">❌</button>
                            </div>`;
                        container.appendChild(newSkillField);
                    }
                    function addExperienceField() {
                        var container = document.getElementById("experienceContainer");
                        var newExperienceEntry = document.createElement("div");
                        newExperienceEntry.className = "experience-entry";
                        newExperienceEntry.innerHTML = `
                            <button class="remove-experience-btn" type="button" onclick="removeExperienceField(this)">❌</button>
                            <div class="group-fields">
                                <div class="section">
                                    <label>Должность</label>
                                    <input type="text" />
                                </div>
                                <div class="section">
                                    <label>Компания</label>
                                    <input type="text" />
                                </div>
                                <div class="section">
                                    <label>Город</label>
                                    <input type="text" />
                                </div>
                            </div>
                            <div class="group-fields">
                                <div class="section">
                                    <label>Дата начала</label>
                                    <input type="date" />
                                </div>
                                <div class="section">
                                    <label>Дата окончания</label>
                                    <input type="date" />
                                </div>
                            </div>
                            <div class="section">
                                <label>Описание</label>
                                <textarea></textarea>
                            </div>
                            <div class="section">
                                <label>Достижения</label>
                                <div class="achievements-container"></div>
                                <button class="add-btn" type="button" onclick="addAchievementField()">➕ Добавить достижение</button>
                            </div>
                            <div class="section">
                                <label>Технологии</label>
                                <div class="technologies-container"></div>
                                <button class="add-btn" type="button" onclick="addTechnologyField()">➕ Добавить технологию</button>
                            </div>`;
                        container.appendChild(newExperienceEntry);
                    }
                    function removeContactField(button) {
                        button.closest('.contact').remove();
                    }
                    function removeSkillField(button) {
                        button.closest('.form-item').remove();
                    }
                    function removeExperienceField(button) {
                        button.closest('.experience-entry').remove();
                    }
                    function addAchievementField() {
                        var container = document.querySelector(".achievements-container");
                        var newAchievementField = document.createElement("div");
                        newAchievementField.className = "form-item";
                        newAchievementField.innerHTML = `
                            <div class="flex-container">
                                <input type="text" placeholder="Достижения" />
                                <button class="remove-btn" type="button" onclick="removeAchievementField(this)">❌</button>
                            </div>`;
                        container.appendChild(newAchievementField);
                    }
                    function removeAchievementField(button) {
                        button.closest('.form-item').remove();
                    }
                    function addTechnologyField() {
                        var container = document.querySelector(".technologies-container");
                        var newTechnologyField = document.createElement("div");
                        newTechnologyField.className = "form-item";
                        newTechnologyField.innerHTML = `
                            <div class="flex-container">
                                <input type="text" placeholder="Технологии" />
                                <button class="remove-btn" type="button" onclick="removeTechnologyField(this)">❌</button>
                            </div>`;
                        container.appendChild(newTechnologyField);
                    }
                    function removeTechnologyField(button) {
                        button.closest('.form-item').remove();
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
                    div("section") {
                        label { +"Контакты" }
                        div {
                            id = "contactsContainer"
                            resume.contacts.forEach { contact ->
                                div("contact") {
                                    div("flex-container") {
                                        div("contact-field") {
                                            textInput { value = contact.title }
                                        }
                                        div("contact-field") {
                                            textInput { value = contact.text }
                                        }
                                        div("contact-field") {
                                            textInput { value = contact.link ?: "" }
                                        }
                                        button(classes = "remove-btn", type = ButtonType.button) {
                                            +"❌"
                                            attributes["onclick"] = "removeContactField(this)"
                                        }
                                    }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить контакт"
                            attributes["onclick"] = "addContactField()"
                        }
                    }
                    div("section") {
                        label { +"Формы сотрудничества" }
                        div("form-group") {
                            resume.cooperationForms.forEach { cooperation ->
                                div("form-item") {
                                    textInput { value = cooperation }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить форму"
                            attributes["onclick"] = "addCooperationField()"
                        }
                    }
                    div("section") {
                        label { +"Навыки" }
                        div("skills-container") {
                            resume.skills.forEach { skill ->
                                div("form-item") {
                                    div("flex-container") {
                                        textInput { value = skill }
                                        button(classes = "remove-btn", type = ButtonType.button) {
                                            +"❌"
                                            attributes["onclick"] = "removeSkillField(this)"
                                        }
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
                    div("section") {
                        label { +"Опыт работы" }
                        div {
                            id = "experienceContainer"
                            resume.experience.forEach { experience ->
                                div("experience-entry") {
                                    button(classes = "remove-experience-btn", type = ButtonType.button) {
                                        +"❌"
                                        attributes["onclick"] = "removeExperienceField(this)"
                                    }
                                    div("group-fields") {
                                        div("section") {
                                            label { +"Должность" }
                                            textInput { value = experience.position }
                                        }
                                        div("section") {
                                            label { +"Название компании" }
                                            textInput { value = experience.companyTitle }
                                        }
                                        div("section") {
                                            label { +"Город компании" }
                                            textInput { value = experience.companyCity }
                                        }
                                    }
                                    div("group-fields") {
                                        div("section") {
                                            label { +"Дата начала" }
                                            input(InputType.date) { value = experience.datePeriod.startDate.toString() }
                                        }
                                        div("section") {
                                            label { +"Дата окончания" }
                                            input(InputType.date) { value = experience.datePeriod.endDate?.toString() ?: "" }
                                        }
                                    }
                                    div("section") {
                                        label { +"Описание" }
                                        textArea { +experience.description }
                                    }
                                    div("section") {
                                        label { +"Достижения" }
                                        div("achievements-container") {
                                            experience.achievements.forEach { achievement ->
                                                div("form-item") {
                                                    div("flex-container") {
                                                        textInput { value = achievement }
                                                        button(classes = "remove-btn", type = ButtonType.button) {
                                                            +"❌"
                                                            attributes["onclick"] = "removeAchievementField(this)"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        button(classes = "add-btn", type = ButtonType.button) {
                                            +"➕ Добавить достижение"
                                            attributes["onclick"] = "addAchievementField()"
                                        }
                                    }
                                    div("section") {
                                        label { +"Технологии" }
                                        div("technologies-container") {
                                            experience.technologies.forEach { technology ->
                                                div("form-item") {
                                                    div("flex-container") {
                                                        textInput { value = technology }
                                                        button(classes = "remove-btn", type = ButtonType.button) {
                                                            +"❌"
                                                            attributes["onclick"] = "removeTechnologyField(this)"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        button(classes = "add-btn", type = ButtonType.button) {
                                            +"➕ Добавить технологию"
                                            attributes["onclick"] = "addTechnologyField()"
                                        }
                                    }
                                }
                            }
                        }
                        button(classes = "add-btn", type = ButtonType.button) {
                            +"➕ Добавить опыт"
                            attributes["onclick"] = "addExperienceField()"
                        }
                    }
                    button { +"Сохранить изменения"; attributes["type"] = "submit" }
                }
                div("message") { id = "message" }
            }
        }
    }.serialize()
}
