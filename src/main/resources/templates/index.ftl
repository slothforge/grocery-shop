<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grocery Shop</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Grocery Shop</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/rest/product-group/all">Product Group API</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Not now</a></li>
            </ul>
        </div>
    </nav>

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2>Практика по спецкурсу "Облачные технологии"</h2>
            <ol>
                <li>Включает в себя приложение или модуль, который предоставляет сервис REST API. <span class="glyphicon glyphicon-remove text-warning"></span></li>
                <li>Включает в себя веб-интерфейс. <span class="glyphicon glyphicon-remove text-danger"></span></li>
                <li>Использует базу данных (рекомендуется PostgreSQL, MySQL). База данных должна содержать не менее 5 таблиц и в общей сложности не менее 20 полей. <span class="glyphicon glyphicon-ok text-success"></span></li>
                <li>Взаимодействует с одной из популярных сторонних систем, таких как Vk, Telegram, smsc.ru и т.д. <span class="glyphicon glyphicon-remove text-danger"></span></li>
                <li>Приложение должно быть развернуто в облаке. <span class="glyphicon glyphicon-ok text-success"></span></li>
                <li>Рекомендуется использовать внешний сервис аутентификации (социальные сети или OpenID). <span class="glyphicon glyphicon-remove text-danger"></span></li>
            </ol>
        </div>
    </div>

</div>
</body>
</html>