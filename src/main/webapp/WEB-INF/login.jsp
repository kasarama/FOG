<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" charset="utf-8" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Fog Carport Designe Centre</title>
    <style>
        body {
            position: relative;
            text-align: center;
            font-family: "Arial Black";
            color: #333333;
        }
        select {
            font-family: "Arial";
        }
        .container2 {
            background-image: url("../images/baggrund3.png");
            background-repeat: no-repeat; /* Do not repeat the image */
            background-size: cover; /* Resize the background image to cover the entire container */
        }
        .tr2 {
            font-family: "Arial";
        }

    </style>

</head>
<body>

<!-- Start Picture -->
<img src="./images/verstTilHjemmeside2.png" alt="Logo" width="100%" height=20%>

<!-- Navigation -->
<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light" style="border-bottom: 2px solid black;">
    <a class="navbar-brand ml-4 c" href="FrontController?target=redirect&destination=index">
        Fog
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse mr-4" id="navbarNavDropdown" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="FrontController?target=redirect&destination=index"><i class="fa fa-fw fa-home"></i>Hjem <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="FrontController?target=redirect&destination=login">Min profil</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container2">
    <div class="col-md-12">
        <div class="row">
            <div class="col text-center">
                <br>
                <br>
                <h2 class="mb-4">Login</h2>
                <form name="login" action="FrontController" method="post">
                    <input type="hidden" name="target" value="login"/>

                    <div class="form-group">
                        <label for="email">Indtast din email:</label>
                        <input type="text" name="email" class="form-control tr2" id="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="password">Indtast dit password:</label>
                        <input type="password" name="password" class="form-control tr2" id="password" placeholder="Password">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-dark mt-3">Login</button>
                    </div>
                </form>
            </div> <!-- /.colonne 1 -->

            <div class="col text-center">
                <br>
                <br>
                <h2 class="mb-4">Registrer</h2>

                <form name="register" action="FrontController" method="post">
                    <input type="hidden" name="target" value="register"/>

                    <div class="form-group">
                        <label for="navn">Indtast dit navn:</label>
                        <input type="text" name="navn" class="form-control tr2" id="navn" aria-describedby="navnHelp" placeholder="Navn">
                    </div>
                    <div class="form-group">
                        <label for="email">Indtast din email:</label>
                        <input type="text" name="email" class="form-control tr2" id="email" aria-describedby="emailHelp" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="password1">Indtast dit password:</label>
                        <input type="password" name="password1" class="form-control tr2" id="password1" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="password2">Indtast dit password igen:</label>
                        <input type="password" name="password2" class="form-control tr2" id="password2" placeholder="Password (gentaget)">
                    </div>

                    <div class="text-center">
                        <!--- TODO - der skal ses på registrer da den laver en 404 fejl efter man trykker registrer -->
                        <button type="submit" class="btn btn-dark mt-3">Registrer</button>
                    </div>
                </form>
            </div> <!-- /.colonne 2 -->
        </div> <!-- /.row -->

        <div class="text-center mt-3">
            <a class="btn btn-outline-dark" href="FrontController?target=redirect&destination=index" role="button">Tilbage til start</a>
        </div>
    </div>
</div> <!-- /.container -->



<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<%@include file="../includes/footer.inc"%>