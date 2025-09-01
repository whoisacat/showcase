package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.EducationDto.Type
import com.whoisacat.showcase.contract.back.dto.ResumeReadingDto
import java.util.LinkedList
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Collectors
import kotlinx.html.DIV
import kotlinx.html.InputType
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.button
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.li
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.onClick
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.span
import kotlinx.html.strong
import kotlinx.html.style
import kotlinx.html.summary
import kotlinx.html.title
import kotlinx.html.ul
import kotlinx.html.unsafe
import mu.KotlinLogging
import org.springframework.stereotype.Service

private const val COOPERATION = "coop"
private const val PERSONS_NAME = "p.name"
private const val PERSONS_LASTNAME = "p.lname"
private const val PERSONS_CITIZENSHIP = "p.ctznp"
private const val PERSONS_CITY = "p.ct"
private const val CONTACTS = "cntcs"
private const val POSITION_LABEL = "lbl"
private const val SETTINGS_CHECKBOX_CLASS = "settings-checkbox"
private const val ABOUT_ME = "me"
private const val KEY_SKILLS = "ksk"
private const val EXPERIENCE = "xpns"
private const val EXPERIENCE_TECH = "xpns.tch"
private const val COMPANY_NAME = "xpns.nm"
private const val COMPANY_CITY = "xpns.ct"
private const val EXPERIENCE_POSITION = "xpns.pstn"
private const val EXPERIENCE_DATE = "xpns.date"
private const val EXPERIENCE_DESCRIPTION = "xpns.dscr"
private const val EXPERIENCE_ACHIEVEMENTS = "xpns.achmts"
private const val EDU = "edu"
private const val EDU_ADDITIONAL = "edu.add"

private const val ABOUT_ME_TITLE = "Обо мне"
private const val KEY_SKILLS_TITLE = "Ключевые навыки"
private const val EXPERIENCE_TITLE = "Опыт работы"
private const val EXPERIENCE_TECH_TITLE = "Технологии"
private const val COMPANY_NAME_TITLE = "Компания"
private const val COMPANY_CITY_TITLE = "Город"
private const val EXPERIENCE_POSITION_TITLE = "Позиция"
private const val EXPERIENCE_DATE_TITLE = "Период"
private const val EXPERIENCE_DESCRIPTION_TITLE = "Описание"
private const val EXPERIENCE_ACHIEVEMENTS_TITLE = "Достижения"
private const val EDU_TITLE = "Образование"
private const val EDU_ADDITIONAL_TITLE = "Повышение квалификации, курсы"
private const val COOPERATION_FORMS_TITLE = "Формы сотрудничества"
private const val CONTACTS_TITLE = "Контакты"

private const val APPLY_SETTINGS = """applySettings"""

@Service
class KotlinXHtmlResumeRenderer : ResumeRenderer {

    private val logger = KotlinLogging.logger {}

    override fun resumePage(resume: ResumeReadingDto, fields: List<String>?) = createHTMLDocument().html {
        head {
            meta(charset = "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
            title { +"Резюме - ${resume.person.firstName} ${resume.person.lastName}" }
            link(rel = "stylesheet", href = "https://fonts.googleapis.com/icon?family=Material+Icons")
            link(rel = "stylesheet", href = "https://fonts.googleapis.com/css?family=Roboto&display=swap")
            script {
                unsafe {
                    raw("""
                        function openModal() {
                            document.getElementById("settingsModal").style.display = "flex";
                        }
                        function closeModal() {
                            document.getElementById("settingsModal").style.display = "none";
                        }
                        function $APPLY_SETTINGS(resumeId) {
                            let checkboxes = document.querySelectorAll('.${SETTINGS_CHECKBOX_CLASS}');
                            let selectedFields = Array.from(checkboxes).filter(cb => cb.checked).map(cb => cb.value);
                            let url = `/resume-page/${'$'}{resumeId}?fields=${'$'}{selectedFields.join(',')}`;
                            window.location.href = url;
                        }
                        function resetSettings(resumeId) {
                            window.location.href = `/resume-page/${'$'}{resumeId}`;
                        }
                        window.onload = function() {
                            document.getElementById("settingsModal").addEventListener("click", function(event) {
                                if (event.target === this) {
                                    closeModal();
                                }
                            });
                        };
                    """)
                }
            }
            style {
                unsafe {
                    raw("""
                        body {
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        .header-container {
                            width: 100%;
                            background-color: #E8EAED;
                        }
                        .header {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            width: 100%;
                            padding: 20px;
                            box-sizing: border-box;
                        }
                        
                        .header div {
                            flex: 1;
                            text-align: center;
                        }
                        .header h1, .header h2 {
                            margin: 0;
                            white-space: nowrap;
                        }
                        .header h1 {
                            align-self: flex-end;
                        }
                        .header h2 {
                            align-self: flex-start;
                            margin-top: auto;
                        }
                        .logo img {
                            max-height: 40px; /* подгоняется под высоту шапки */
                            height: auto;
                            width: auto;
                            display: block;
                        }
                        .content {
                            max-width: 750px;
                            width: 100%;
                            padding: 20px;
                            box-sizing: border-box;
                        }
                        .info-container {
                            display: flex;
                            justify-content: space-between;
                            margin-bottom: 20px;
                        }
                        .work-label, .contacts {
                            flex: 1;
                            padding: 10px;
                            box-sizing: border-box;
                            text-align: left;
                        }
                        .skills {
                            display: flex;
                            flex-wrap: wrap;
                            gap: 10px;
                        }
                        .skill {
                            background-color: #e0e4e8;
                            border: 1px solid #d0d7de;
                            border-radius: 6px;
                            padding: 8px 12px;
                            font-size: 14px;
                            color: #0366d6;
                        }
                        .tech-list {
                            display: flex;
                            flex-wrap: wrap;
                            list-style: none;
                            padding: 0;
                        }
                        .tech-list li {
                            background-color: #e0e4e8;
                            border: 1px solid #d0d7de;
                            border-radius: 6px;
                            padding: 8px 12px;
                            font-size: 14px;
                            color: #0366d6;
                            margin: 5px;
                        }
                        h1 {
                            font-size: calc(20px + 1.5vw);
                        }
                        h2, p {
                            font-size: calc(16px + 0.5vw);
                        }
                        @media screen and (max-width: 700px) {
                            .header {
                                flex-direction: column;
                                align-items: center;
                            }
                            .header h1 {
                                font-size: calc(20px + 2vw);
                            }
                            .header h2 {
                                font-size: calc(16px + 1.5vw);
                                white-space: normal;
                                text-align: center;
                            }
                            .contacts p {
                                font-size: calc(12px + 0.8vw);
                            }
                            h2 {
                                font-size: calc(18px + 1.5vw);
                            }
                            p {
                                font-size: calc(14px + 1vw);
                            }
                            .info-container {
                                flex-direction: column;
                                align-items: left;
                            }
                            .contacts, .work-label {
                                width: 100%;
                                text-align: left;
                                padding: 10px 0;
                            }
                        }
                        .floating-print-button, .floating-settings-button {
                            position: fixed;
                            bottom: 10px;
                            width: 50px;
                            height: 50px;
                            background-color: transparent; /* Прозрачный фон */
                            color: #E8EAED; /* Цвет иконки */
                            border: 2px solid #E8EAED; /* Цвет и толщина контура */
                            border-radius: 50%;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-size: 24px;
                            cursor: pointer;
                            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                            z-index: 1000;
                            transition: background-color 0.3s, color 0.3s; /* Плавный переход для фона и цвета */
                        }
                        .floating-print-button { right: 10px; }
                        .floating-settings-button { right: 70px; }
                        .floating-print-button:hover, .floating-settings-button:hover {
                            background-color: #E8EAED;
                            color: white;
                        }
                        @media print {
                            .floating-print-button, .floating-settings-button {
                                display: none;
                            }
                        }
                        .modal {
                            display: none;
                            position: fixed;
                            z-index: 1000;
                            left: 0;
                            top: 0;
                            width: 100%;
                            height: 100%;
                            background-color: rgba(0, 0, 0, 0.5);
                            justify-content: center;
                            align-items: center;
                        }
                        .modal-content {
                            background: white;
                            padding: 20px;
                            border-radius: 10px;
                            text-align: left;
                            width: 90%;
                            max-width: 400px;
                            max-height: 90vh; /* Ограничение по высоте */
                            overflow-y: auto; /* Включаем вертикальную прокрутку */
                            position: fixed;
                            top: 50%;
                            left: 50%;
                            transform: translate(-50%, -50%);
                        }
                        .modal button {
                            margin-top: 10px;
                            padding: 10px;
                            width: 100%;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                        }
                        .modal button:first-of-type {
                            background-color: #6200ea;
                            color: white;
                        }
                        .modal button:last-of-type {
                            background-color: #ccc;
                        }
                        .checkbox-container {
                            display: flex;
                            flex-direction: column;
                            gap: 10px;
                        }
                        .checkbox-container label {
                            display: flex;
                            align-items: center;
                            gap: 10px;
                            cursor: pointer;
                        }
                        .floating-settings-button {
                            position: fixed;
                            bottom: 10px;
                            right: 70px;
                            width: 50px;
                            height: 50px;
                            background-color: #6200ea;
                            color: white;
                            border: none;
                            border-radius: 50%;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-size: 24px;
                            cursor: pointer;
                            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                        }
                    """.trimIndent())
                }
            }
        }
        body {
            div("header-container") {
                div("header") {
                    div("logo") {
                        a(href = "/") {
                            img(src = "/img.png", alt = "logo")
                        }
                    }
                    div {

                        if (fields?.contains("person") != false) {
                            h1 { +"${resume.person.firstName} ${resume.person.lastName}" }
                        } else {
                            var name = ""
                            if (fields.contains(PERSONS_NAME)) name = name + resume.person.firstName + " "
                            if (fields.contains(PERSONS_LASTNAME)) name = name + resume.person.lastName + " "
                            if (name.isNotEmpty()) h1 { +name.trim() }
                        }
                    }
                    div {
                        if (fields?.contains("person") != false || fields.contains(PERSONS_CITY)) {
                            h2 { +resume.person.city }
                        }
                    }
                }
            }
            div("content") {
                div("info-container") {
                    if (fields?.contains(CONTACTS) != false) {
                        div("contacts") {
                            resume.contacts.forEach {
                                p {
                                    strong { +"${it.title}: " }
                                    a(href = it.link) { +it.text }
                                }
                            }
                        }
                    } else {
                        div("contacts") {
                            addListWithOptionalItems(
                                fields = fields,
                                set = resume.contacts,
                                resumeBlockName = CONTACTS,
                                consumer = {
                                    p {
                                        strong { +"${it.title}: " }
                                        a(href = it.link) { +it.text }
                                    }
                                }
                            )
                        }
                    }
                    div("work-label") {
                        if (fields?.contains(POSITION_LABEL) != false) h1 { +resume.label }
                        if (fields?.contains(COOPERATION) != false) {
                            p { strong { +"$COOPERATION_FORMS_TITLE:" } }
                            ul {
                                addListWithOptionalItems(
                                    fields = fields,
                                    set = resume.cooperationForms,
                                    resumeBlockName = COOPERATION,
                                    consumer = {
                                        li {
                                            +it
                                        }
                                    }
                                )
                            }
                        }
                        span {
                            if (fields?.contains("person") != false || fields.contains(PERSONS_CITIZENSHIP)) {
                                strong { +"Гражданство:" }
                                +resume.person.citizenship
                            }
                        }

                    }
                }
                if (fields?.contains(ABOUT_ME) != false) {
                    h2 { +ABOUT_ME_TITLE }
                    p { +resume.aboutMe }
                }

                if (resume.skills.isNotEmpty() && fields?.contains(KEY_SKILLS) != false) {
                    h2 { +KEY_SKILLS_TITLE }
                    div("skills") {
                        resume.skills.forEach {
                            div("skill") { +it }
                        }
                    }
                }
                if (resume.experience.isNotEmpty()
                    && (fields?.contains(EXPERIENCE) != false
                            || fields.contains(EXPERIENCE_TECH))) {
                    h2 { +EXPERIENCE_TITLE }
                }
                if (resume.experience.isNotEmpty()
                    && fields?.contains(EXPERIENCE) != false) {
                    addListWithOptionalItems(
                        fields = fields,
                        set = resume.experience,
                        resumeBlockName = EXPERIENCE,
                        consumer = {
                            val company = LinkedList<String>()
                            if (fields?.contains(COMPANY_NAME) != false) company.add(it.companyTitle)
                            if (fields?.contains(COMPANY_CITY) != false) company.add(it.companyCity)
                            if (company.isNotEmpty()) h3 { +company.stream().collect(Collectors.joining(", ")) }
                            if (fields?.contains(EXPERIENCE_POSITION) != false) p { strong { +it.position } }
                            logger.trace { "date ${fields?.contains(EXPERIENCE_DATE) != false} t.datePeriod ${it.datePeriod}" }
                            if (fields?.contains(EXPERIENCE_DATE) != false) p { strong { +it.datePeriod } }
                            if (fields?.contains(EXPERIENCE_DESCRIPTION) != false) p { +it.description }
                            if (fields?.contains(EXPERIENCE_ACHIEVEMENTS) != false && it.achievements.isNotEmpty()) {
                                p { +"$EXPERIENCE_ACHIEVEMENTS_TITLE:" }
                                ul {
                                    it.achievements.forEach { achievement ->
                                        li { +achievement }
                                    }
                                }
                            }
                            if (fields?.contains(EXPERIENCE_TECH) != false && it.technologies.isNotEmpty()) {
                                details {
                                    summary { +EXPERIENCE_TECH_TITLE }
                                    ul("tech-list") {
                                        it.technologies.forEach { technology ->
                                            li { +technology }
                                        }
                                    }
                                }
                            }
                        })
                }
                if (fields?.contains(EDU) != false && resume.edu.isNotEmpty()) {
                    val mainEdu = resume.edu.filter { it.type == Type.MAIN }
                    if (mainEdu.isNotEmpty()) {
                        h2 { +EDU_TITLE }
                        mainEdu.sorted().forEach {
                            h3 { +it.degree!! }
                            p { +"${it.graduationDate} ${it.institution} ${it.residenceCity}" }
                            p { +it.faculty!! }
                            p { +it.speciality }
                        }
                    }

                    val trainings = resume.edu.filter { it.type == Type.TRAINING }
                    if (fields?.contains(EDU_ADDITIONAL) != false && trainings.isNotEmpty()) {
                        h2 { +EDU_ADDITIONAL_TITLE }
                        trainings.sorted().forEach {
                            p { +"${it.graduationDate} ${it.institution} ${it.speciality}" }
                        }
                    }
                }
            }

            div("modal") {
                id = "settingsModal"
                style = "display: none;"
                div("modal-content") {
                    h2 { +"Настройки отображения" }
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = PERSONS_NAME; if (fields?.contains(PERSONS_NAME) == true) checked = true
                        }
                        +" Имя"
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = PERSONS_LASTNAME; if (fields?.contains(PERSONS_LASTNAME) == true) checked = true
                        }
                        +" Фамилия"
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = PERSONS_CITY; if (fields?.contains(PERSONS_CITY) == true) checked = true
                        }
                        +" Город"
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = CONTACTS; if (fields?.contains(CONTACTS) == true) checked = true
                        }
                        +CONTACTS_TITLE
                    }
                    checksForList(
                        resume.contacts,
                        fields,
                        appendedStringSupplier = { it.title },
                        null,
                        CONTACTS
                    )
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = POSITION_LABEL; if (fields?.contains(POSITION_LABEL) == true) checked = true
                        }
                        +"Должность"
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = COOPERATION; if (fields?.contains(COOPERATION) == true) checked = true
                        }
                        +COOPERATION_FORMS_TITLE
                    }
                    checksForList(
                        resume.cooperationForms,
                        fields,
                        appendedStringSupplier = { it },
                        COOPERATION_FORMS_TITLE,
                        COOPERATION
                    )
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = PERSONS_CITIZENSHIP; if (fields?.contains(PERSONS_CITIZENSHIP) == true) checked = true
                        }
                        +"Гражданство"
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = ABOUT_ME; if (fields?.contains(ABOUT_ME) == true) checked = true
                        }
                        +ABOUT_ME_TITLE
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = KEY_SKILLS; if (fields?.contains(KEY_SKILLS) == true) checked = true
                        }
                        +KEY_SKILLS_TITLE
                    }
                    br()
                    label {
                        input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                            value = EXPERIENCE; if (fields?.contains(EXPERIENCE) == true) checked = true
                        }
                        +EXPERIENCE_TITLE
                    }
                    checksForList(
                        resume.experience,
                        fields,
                        appendedStringSupplier = { "${it.companyTitle}  ${it.companyCity}  ${it.datePeriod}" },
                        EXPERIENCE_TITLE,
                        EXPERIENCE
                    )
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = COMPANY_NAME; if (fields?.contains(COMPANY_NAME) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$COMPANY_NAME_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = COMPANY_CITY; if (fields?.contains(COMPANY_CITY) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$COMPANY_CITY_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EXPERIENCE_POSITION; if (fields?.contains(EXPERIENCE_POSITION) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$EXPERIENCE_POSITION_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EXPERIENCE_DATE; if (fields?.contains(EXPERIENCE_DATE) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$EXPERIENCE_DATE_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EXPERIENCE_DESCRIPTION; if (fields?.contains(EXPERIENCE_DESCRIPTION) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$EXPERIENCE_DESCRIPTION_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EXPERIENCE_ACHIEVEMENTS; if (fields?.contains(EXPERIENCE_ACHIEVEMENTS) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$EXPERIENCE_ACHIEVEMENTS_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EXPERIENCE_TECH; if (fields?.contains(EXPERIENCE_TECH) == true) checked = true }
                        +"$EXPERIENCE_TITLE.$EXPERIENCE_TECH_TITLE"
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EDU; if (fields?.contains(EDU) == true) checked = true }
                        +EDU_TITLE
                    }
                    br()
                    label { input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS)
                    {
                        value = EDU_ADDITIONAL; if (fields?.contains(EDU_ADDITIONAL) == true) checked = true }
                        +"$EDU_TITLE.$EDU_ADDITIONAL_TITLE"
                    }
                    br()
                    button {
                        logger.trace { "resume id ${resume.id}" }
                        onClick = "$APPLY_SETTINGS('${resume.id}')"
                        +"Применить"
                    }
                    button {
                        onClick = "resetSettings('${resume.id}')"
                        +"Сбросить все"
                    }
                }
            }
            button(classes = "floating-print-button") {
                span("material-icons") {
                    +"print"
                    onClick = "window.print()"
                }
            }
            button(classes = "floating-settings-button") {
                span("material-icons") {
                    +"build"
                    onClick = "openModal()"
                }
            }
        }
    }.serialize()

    private fun <T> DIV.checksForList(
        set: Set<T>,
        fields: List<String>?,
        appendedStringSupplier: Function<T, String>,
        titlePrefix: String?,
        parameterPrefix: String
    ) {
        var c = 0
        set.forEach {
            br()
            label {
                input(type = InputType.checkBox, classes = SETTINGS_CHECKBOX_CLASS) {
                    value = "${parameterPrefix}.${++c}"; if (fields?.contains("${parameterPrefix}.$c") == true) checked = true
                }
                +"${if(titlePrefix.isNullOrBlank()) "" else "$titlePrefix - "} ${appendedStringSupplier.apply(it)} "
            }
        }
    }

    private fun <T> addListWithOptionalItems(
        fields: List<String>?,
        set: Set<T>,
        consumer: Consumer<T>,
        resumeBlockName: String
    ) {
        val itemsForPrinting: Set<Int> = (fields ?: emptyList()).stream()
            .filter { it.matches(Regex("${resumeBlockName}\\.\\d+")) }
            .map { it.replace("$resumeBlockName.", "") }
            .map { Integer.parseInt(it) }
            .collect(Collectors.toSet())
        logger.trace { "itemsForPrinting $itemsForPrinting" }
        var c = 0
        set.forEach exp@ {
            if (itemsForPrinting.isNotEmpty() && !itemsForPrinting.contains(++c)) return@exp
            consumer.accept(it)
        }
    }
}
