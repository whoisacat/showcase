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
            style {
                unsafe {
                    raw(
                        """
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
                            background-color: #d7DadC; /* бирюзовый цвет */
                        }
                        .header {
                            display: flex;
                            justify-content: space-between;
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
                    """.trimIndent()
                )
            }
        }
        script(src = "https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js") {}
        script(src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.1/jspdf.umd.min.js") {}
        script {
            unsafe {
                raw(
                    """
                        document.addEventListener("DOMContentLoaded", function() {
                            document.getElementById("download-pdf").addEventListener("click", function() {
                                const { jsPDF } = window.jspdf;

                        html2canvas(document.body).then(canvas => {
                            const imgData = canvas.toDataURL('image/png');
                            const pdf = new jsPDF('p', 'mm', 'a4');
                            const pdfWidth = pdf.internal.pageSize.getWidth();
                            const pdfHeight = pdf.internal.pageSize.getHeight();
                            const imgProps = pdf.getImageProperties(imgData);
                            const imgHeight = (imgProps.height * pdfWidth) / imgProps.width;

                            const headerHeight = 10;
                            const footerHeight = 10;
                            const effectivePdfHeight = pdfHeight - headerHeight - footerHeight;

                            const addHeaderFooter = (pdf, pageNumber) => {
                                pdf.setFontSize(10);
                                pdf.text('Header', pdfWidth / 2, headerHeight / 2, { align: 'center' });
                                pdf.text('Footer', pdfWidth / 2, pdfHeight - footerHeight / 2, { align: 'center' });
                            };

                            let heightLeft = imgHeight;
                            let position = 0;
                            let pageNumber = 1;

                            while (heightLeft > 0) {
                                let pageCanvas = document.createElement('canvas');
                                pageCanvas.width = canvas.width;
                                pageCanvas.height = (effectivePdfHeight * canvas.width) / pdfWidth;

                                let pageContext = pageCanvas.getContext('2d');
                                pageContext.drawImage(canvas, 0, position * canvas.width / pdfWidth, canvas.width, pageCanvas.height, 0, 0, canvas.width, pageCanvas.height);

                                let pageImgData = pageCanvas.toDataURL('image/png');
                                if (pageNumber > 1) pdf.addPage();
                                pdf.addImage(pageImgData, 'PNG', 0, headerHeight, pdfWidth, effectivePdfHeight);
                                addHeaderFooter(pdf, pageNumber);

                                heightLeft -= effectivePdfHeight;
                                position += effectivePdfHeight;
                                pageNumber++;
                            }

                            pdf.save('resume.pdf');
                        });
                    });
                });
            """.trimIndent()
        )
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
                button {
                    id = "download-pdf"
                    +"Скачать в PDF"
                }
                div("info-container") {
                    div("contacts") {
                        summary.contacts.stream().map {
                            p {
                                strong { +"${it.title}: " }
                                a(href = it.link) { +it.text }
                            }
                        }.toList()
                    }
                    div("work-label") {
                        h1 { +summary.label }
                        p {
                            strong { +"Формы сотрудничества:" }
                            ul {
                                summary.cooperationForms.stream()
                                    .map { li { +it } }
                                    .toList()
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

                if (!summary.skills.isEmpty()) {
                    h2 { +"Ключевые навыки" }
                    div("skills") {
                        summary.skills.stream()
                            .map {
                                div("skill") { +it }
                            }
                            .toList()
                    }
                }
                if (!summary.expirience.isEmpty()) {
                    h2 { +"Опыт работы" }
                    summary.expirience.stream()
                        .map {
                            h3 { +"${it.companyTitle}, ${it.companyCity}" }
                            p { strong { +it.position } }
                            p { strong { +it.datePeriod.toString() } }
                            p { +it.description }
                            if (!it.achievements.isEmpty()) {
                                p { +"Достижения:" }
                                ul {
                                    it.achievements.stream()
                                        .map { li { +it } }
                                        .toList()
                                }
                            }
                            if (!it.technologies.isEmpty()) {
                                details {
                                    summary { +"Технологии" }
                                    ul("tech-list") {
                                        it.technologies.stream()
                                            .map { li { +it } }
                                            .toList()
                                    }
                                }
                            }
                        }
                        .toList()
                }
                if (!summary.edu.isEmpty()) {
                    val mainEdu = summary.edu.stream()
                        .filter { it.type.equals(Type.MAIN) }
                        .toList()
                    if (!mainEdu.isEmpty()) {
                        h2 { +"Образование" }
                        mainEdu
                            .sorted()
                            .map {
                                h3 { +it.degree!! }
                                p { +"${it.graduationDate} ${it.institution} ${it.residenceCity}" }
                                p { +it.faculty!! }
                                p { +it.speciality }
                            }
                            .toList()
                    }

                    val trainings = summary.edu.stream()
                        .filter { it.type.equals(Type.TRAINING) }
                        .toList()
                    if (!trainings.isEmpty()) {
                        h2 { +"Повышение квалификации, курсы" }
                        trainings
                            .sorted()
                            .map {
                                p { +"${it.graduationDate} ${it.institution} ${it.speciality}" }
                            }
                            .toList()
                    }
                }
            }
        }
    }.serialize()
}
