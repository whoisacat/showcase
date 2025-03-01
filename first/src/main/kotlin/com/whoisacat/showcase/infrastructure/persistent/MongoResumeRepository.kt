package com.whoisacat.showcase.infrastructure.persistent

import com.whoisacat.showcase.domain.entity.Contact
import com.whoisacat.showcase.domain.entity.DatePeriod
import com.whoisacat.showcase.domain.entity.Education
import com.whoisacat.showcase.domain.entity.Expirience
import com.whoisacat.showcase.domain.entity.Person
import com.whoisacat.showcase.domain.entity.Resume
import com.whoisacat.showcase.domain.infrastructure.ResumeRepository
import com.whoisacat.showcase.infrastructure.repository.ResumeDao
import java.lang.RuntimeException
import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class MongoResumeRepository(private val dao: ResumeDao) : ResumeRepository {

    override fun save(resume: Resume) {
        dao.save(resume)
    }

    override fun get(): Resume {
        val resume = Resume(
            person = Person(
                lastName = "Цыпляшов",
                firstName = "Антон",
                birthDate = LocalDate.of(1987, 7, 13),
                city = "Екатеринбург",
                citizenship = "Россия"
            ),
            contacts = setOf(
                Contact(title = "email", link = "mailto:whoisacat@gmail.com", text = "whoisacat@gmail.com"),
                Contact(
                    title = "linkedIn",
                    link = "https://linkedin.com/in/whoisacat/",
                    text = "linkedin.com/in/whoisacat"
                ),
                Contact(title = "telegram", link = "https://t.me/whoisacat", text = "@whoisacat"),
                Contact(title = "github", link = "https://github.com/whoisacat\"", text = "github.com/whoisacat"),
            ),
            label = "BackEnd Техлид (kotlin, java)",
            cooperationForms = setOf("Найм на удаленный формат", "Проект", "Менторство", "Аудит"),
            aboutMe = """
                Увлеченный разработчик с жаждой знаний и страстью к обмену опытом. Моя карьера охватывает широкий 
                спектр технологий: от схемотехники и низкоуровневого ПО для ракетно-космической отрасли до управления 
                ИТ в медиа. Сейчас моя основная специализация - бэкэнд-разработка: более 4 лет опыта, от систем сбора 
                данных до микросервисов с высоконагруженными ETL процессами. Я также интересуюсь архитектурой систем и 
                участвую в профессиональных сообществах. В свободное время люблю сноуборд, волейбол и путешествия, 
                иногда рисую пальмочки в углу листа бумаги.""".trimIndent(),
            expirience = setOf(
                Expirience(
                    position = "Программист (back)",
                    datePeriod = DatePeriod(LocalDate.of(2024, 2, 15)),
                    companyTitle = "Системные решения",
                    companyCity = "Москва",
                    description = "Разработка бэкенд части BI системы. Во время работы удалось:",
                    achievements = setOf(
                        "вернуть юнит тестирование в проекты",
                        "внедрить интеграционное тестирование",
                        "внедрить поиск через opensearch",
                        "ускорить формирование нескольких отчетов (примерно в 20-200 раз)",
                        "реализовать порядка 20 фич в проект",
                        "исправить 4 архитектурных недоработки"
                    ),
                    technologies = setOf("java21", "spring", "maven", "postgres", "apache poi")
                ),
                Expirience(
                    position = "Программист (back)",
                    datePeriod = DatePeriod(
                        LocalDate.of(2022, 12, 1),
                        LocalDate.of(2024, 1, 31)
                    ),
                    companyTitle = "Газпромбанк, ОАО",
                    companyCity = "Москва",
                    description = """Автоматизация, стандартизация, унификация решений администрирования процессов и 
                        администрированием процессов разработки. Самостоятельно либо во главе ситуационной группы""",
                    achievements = setOf(
                        "вернуть юнит тестирование в проекты",
                        "создал 4 приложения различной сложности",
                        "внедрил трассировку в проекты департамента, что снизило скорость расследования инцидентов (~40%);",
                        "администрированием процессов снял нагрузку с руководителя управления"
                    ),
                    technologies = setOf(
                        "java 8",
                        "java 11",
                        "java 17",
                        "Spring Boot",
                        "Spring Kafka",
                        "gradle",
                        "JAXB",
                        "OTLP",
                        "Spring Shell",
                        "Keycloak API"
                    )
                )
            ),
            edu = setOf(
                Education(
                    Education.Type.MAIN,
                    residenceCity = "Екатеринбург",
                    institution = "Уральский федеральный университет имени первого Президента России Б.Н. Ельцина",
                    graduationDate = 2016,
                    faculty = "Институт радиоэлектроники и информационных технологий - РтФ",
                    speciality = "Управление в технических системах",
                    degree = "бакалавр"
                ),
                Education(
                    Education.Type.TRAINING,
                    residenceCity = "Москва",
                    institution = "Отус Онлайн-Образование",
                    graduationDate = 2021,
                    faculty = null,
                    speciality = "Разработчик на Spring Framework",
                    degree = null
                ),
                Education(
                    Education.Type.TRAINING,
                    residenceCity = "Москва",
                    institution = "Отус Онлайн-Образование",
                    graduationDate = 2024,
                    faculty = null,
                    speciality = "Алгоритмы и структуры данных",
                    degree = null
                )
            ),
            skills = setOf(
                "Java",
                "Git",
                "Intellij IDEA",
                "JUnit",
                "Bash",
                "JPA",
                "Liquibase",
                "REST",
                "PostgreSQL",
                "Spring Framework",
                "Linux",
                "JProfiler",
                "Kotlin",
                "Android SDK",
                "Apache Kafka",
                "Gradle",
                "DDD"
            )
        )
//        dao.save(resume)
        return resume
    }

    override fun get(id: String): Resume {
        return dao.findById(id).orElseThrow {RuntimeException("Resume not found by id $id")}
    }
}