package com.whoisacat.showcase.view.renderer

import com.whoisacat.showcase.contract.back.dto.ResumeListDto
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.html
import kotlinx.html.img
import kotlinx.html.li
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.ul
import kotlinx.html.unsafe
import org.springframework.stereotype.Service

@Service
class MainPageRenderer {

    fun render(
        username: String?,
        publicResumes: List<ResumeListDto>,
        editableResumes: List<ResumeListDto>
    )  = createHTMLDocument().html {
        head {
            meta(charset = "UTF-8")
            title("Резюмешница")
            style {
                unsafe {
                    +"""
                body {
                    font-family: sans-serif;
                    margin: 0;
                    padding: 0;
                    display: flex;
                    flex-direction: column;
                    min-height: 100vh;
                }
                header {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    padding: 1rem 2rem;
                    background: #f8f9fa;
                    border-bottom: 1px solid #ddd;
                }
                header img {
                    height: 40px;
                }
                main {
                    flex: 1;
                    padding: 2rem;
                }
                section {
                    margin-bottom: 2rem;
                }
                h1, h2 {
                    margin-bottom: 0.5rem;
                }
                ul {
                    list-style: none;
                    padding: 0;
                }
                li {
                    margin: 0.25rem 0;
                }
                footer {
                    background: #f1f3f5;
                    padding: 2rem;
                    text-align: center;
                    border-top: 1px solid #ddd;
                }
                .btn {
                    display: inline-block;
                    padding: 0.5rem 1rem;
                    border-radius: 6px;
                    background: #007bff;
                    color: white;
                    text-decoration: none;
                }
                .btn:hover {
                    background: #0056b3;
                }
                """
                }
            }
        }
        body {
            header {
                div {
                    img(src = "/img.png", alt = "logo")
                }
                if (username == null) {
                    a("/oauth2/authorization/keycloak", classes = "btn") { +"Войти" }
                } else {
                    a("/logout", classes = "btn") { +"Выйти" }
                }
            }
            main {
                section {
                    h1 { +"Welcome to show-case${if (username == null) "" else ", $username!!!"}" }
                    p {
                        +"Здесь вы можете просматривать и создавать резюме с гибкой настройкой отображаемой информации."
                    }
                }

                section {
                    h2 { +"Список резюме" }
                    if (publicResumes.isEmpty()) {
                        p { +"Пока нет доступных резюме." }
                    } else {
                        ul {
                            publicResumes.forEach { resume ->
                                li {
                                    a("/resume-page/${resume.id}") {
                                        +"${resume.label} ${resume.person.firstName} ${resume.person.lastName}"
                                    }
                                }
                            }
                        }
                    }
                }

                if (username != null) {
                    section {
                        h2 { +"Мои резюме (для редактирования)" }
                        if (editableResumes.isEmpty()) {
                            p { +"У вас пока нет резюме для редактирования." }
                        } else {
                            ul {
                                editableResumes.forEach { resume ->
                                    li {
                                        a("/resume-editor/${resume.id}") {
                                            +"${resume.label} ${resume.person.firstName} ${resume.person.lastName}"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            footer {
                h2 { +"Want to collaborate or submit your resume?." }
                p {
                    +"Contact me using the details below: "
                    a(href = "mailto:whoisacat@gmail.com") { +"whoisacat@gmail.com" }
                    +" | "
                    a(href = "https://t.me/whoisacat") { +"Telegram" }
                }
            }
        }
    }.serialize()
}