package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.domain.entity.Summary
import com.whoisacat.showcase.domain.entity.Type
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.stereotype.Service

@Service
class KotlinXHtmlSummaryRenderer : SummaryRenderer {
    override fun summaryPage(summary: Summary) = createHTMLDocument().html {
        head {
            meta(charset = "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
            title { +"Резюме - ${summary.person.firstName} ${summary.person.lastName}" }
            link(rel = "stylesheet", href = "https://fonts.googleapis.com/icon?family=Material+Icons")
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
                        .floating-print-button {
                            position: fixed;
                            bottom: 10px;
                            right: 10px;
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
                        .floating-print-button:hover {
                            background-color: #E8EAED; /* Цвет фона при наведении */
                            color: white; /* Цвет иконки при наведении */
                        }
                        @media print {
                            .floating-print-button {
                                display: none;
                            }
                        }
                    """.trimIndent())
                }
            }
        }
        body {
            div("header-container") {
                div("header") {
                    div {
                        h1 { +"${summary.person.firstName} ${summary.person.lastName}" }
                    }
                    div {
                        h2 { +summary.person.city }
                    }
                }
            }
            div("content") {
                div("info-container") {
                    div("contacts") {
                        summary.contacts.forEach {
                            p {
                                strong { +"${it.title}: " }
                                a(href = it.link) { +it.text }
                            }
                        }
                    }
                    div("work-label") {
                        h1 { +summary.label }
                        p {
                            strong { +"Формы сотрудничества:" }
                            ul {
                                summary.cooperationForms.forEach {
                                    li { +it }
                                }
                            }
                        }
                        span {
                            strong { +"Гражданство:" }
                            +summary.person.cityzenship
                        }
                    }
                }

                h2 { +"Обо мне" }
                p { +summary.aboutMe }

                if (summary.skills.isNotEmpty()) {
                    h2 { +"Ключевые навыки" }
                    div("skills") {
                        summary.skills.forEach {
                            div("skill") { +it }
                        }
                    }
                }
                if (summary.expirience.isNotEmpty()) {
                    h2 { +"Опыт работы" }
                    summary.expirience.forEach {
                        h3 { +"${it.companyTitle}, ${it.companyCity}" }
                        p { strong { +it.position } }
                        p { strong { +it.datePeriod.toString() } }
                        p { +it.description }
                        if (it.achievements.isNotEmpty()) {
                            p { +"Достижения:" }
                            ul {
                                it.achievements.forEach { achievement ->
                                    li { +achievement }
                                }
                            }
                        }
                        if (it.technologies.isNotEmpty()) {
                            details {
                                summary { +"Технологии" }
                                ul("tech-list") {
                                    it.technologies.forEach { technology ->
                                        li { +technology }
                                    }
                                }
                            }
                        }
                    }
                }
                if (summary.edu.isNotEmpty()) {
                    val mainEdu = summary.edu.filter { it.type == Type.MAIN }
                    if (mainEdu.isNotEmpty()) {
                        h2 { +"Образование" }
                        mainEdu.sorted().forEach {
                            h3 { +it.degree!! }
                            p { +"${it.graduationDate} ${it.institution} ${it.residenceCity}" }
                            p { +it.faculty!! }
                            p { +it.speciality }
                        }
                    }

                    val trainings = summary.edu.filter { it.type == Type.TRAINING }
                    if (trainings.isNotEmpty()) {
                        h2 { +"Повышение квалификации, курсы" }
                        trainings.sorted().forEach {
                            p { +"${it.graduationDate} ${it.institution} ${it.speciality}" }
                        }
                    }
                }
            }
            button(classes = "floating-print-button") {
                span("material-icons") {
                    +"print"
                    onClick = "window.print()"
                }
            }
        }
    }.serialize()
}
