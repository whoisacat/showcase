package com.whoisacat.showcase.view.renderer

import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.img
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.pre
import kotlinx.html.style
import kotlinx.html.summary
import kotlinx.html.title
import kotlinx.html.unsafe
import org.springframework.stereotype.Service

@Service
class ErrorPageRenderer {

    fun errorPage(message: String? = null) = createHTMLDocument().html {
        head {
            meta(charset = "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
            title { +"Ошибка" }
            style {
                unsafe {
                    raw("""
                        body {
                            margin: 0;
                            padding: 0;
                            font-family: Roboto, sans-serif;
                            background-color: #f5f5f5;
                            color: #333;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                        }
                        .container {
                            text-align: center;
                        }
                        .logo-container {
                            position: absolute;
                            top: 20px;
                            right: 20px;
                        }
                        .logo-container img {
                            max-height: 40px;
                            width: auto;
                            display: block;
                        }
                        h1 {
                            font-size: 32px;
                            margin-bottom: 10px;
                        }
                        p {
                            font-size: 18px;
                            color: #666;
                        }
                        a.home-link {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 10px 20px;
                            background-color: #6200ea;
                            color: #fff;
                            text-decoration: none;
                            border-radius: 6px;
                        }
                        a.home-link:hover {
                            background-color: #4500b5;
                        }
                        details {
                            margin-top: 20px;
                            padding: 10px;
                            border: 1px solid #ccc;
                            border-radius: 6px;
                            display: inline-block;
                            text-align: left;
                        }
                        summary {
                            cursor: pointer;
                            font-weight: bold;
                        }
                        pre {
                            white-space: pre-wrap;
                            word-wrap: break-word;
                            background: #f8f8f8;
                            padding: 10px;
                            border-radius: 4px;
                            overflow-x: auto;
                        }
                    """.trimIndent())
                }
            }
        }
        body {
            div("logo-container") {
                a(href = "/") {
                    img(src = "/img.png", alt = "logo")
                }
            }
            div("container") {
                h1 { +"Что-то пошло не так" }
                p {
                    +"Произошла ошибка. Пожалуйста, попробуйте позже."
                }
                details {
                    summary { +"Показать подробности" }
                    pre {
                        unsafe {
                            + (message ?: "Нет дополнительных деталей")
                        }
                    }
                }
                a(href = "/", classes = "home-link") { +"На главную" }
            }
        }
    }.serialize()
}
