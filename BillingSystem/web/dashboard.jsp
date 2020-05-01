<%-- 
    Document   : dashboard
    Created on : May 1, 2020, 3:03:27 PM
    Author     : moham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Database_Tables.*"%>
<%@page import="database.Database"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Billing System - ITI</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="./css/mdb.min.css" rel="stylesheet">
        <link href="./css/style.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>

    <style>
        .pt-5,
        .py-5 {
            margin-bottom: 425px;
        }
    </style>

    <body class="grey lighten-3">
        <!--Main Navigation-->
        <header>
            <!-- Sidebar -->
            <div class="sidebar-fixed position-fixed">
                <a class="logo-wrapper waves-effect">
                    <img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">
                </a>

                <div class="list-group list-group-flush">
                    <a href="dashboard.jsp" class="list-group-item active waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Dashboard
                    </a>
                    <a href="addCustomer.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-user mr-3"></i>Add Customer</a>

                </div>

            </div>
            <!-- Sidebar -->
        </header>
        <!--Main Navigation-->

        <!--Main layout-->
        <main class="pt-5 mx-lg-5">
            <div class="container-fluid mt-5">

                <!-- Heading -->
                <div class="card mb-4 wow fadeIn">

                    <!--Card content-->
                    <div class="card-body d-sm-flex justify-content-between">

                        <h4 class="mb-2 mb-sm-0 pt-1">
                            <a href="dashboard.jsp" target="_blank">Dashboard</a>
                        </h4>

                        <form class="d-flex justify-content-center" action="searchResult.jsp" method="POST">
                            <!-- Default input -->
                            <input type="search" placeholder="Find a customer" aria-label="Search" class="form-control" name="keyword">
                            <button class="btn btn-primary btn-sm my-0 p" type="submit">
                                <i class="fas fa-search"></i>
                            </button>

                        </form>

                    </div>

                </div>
                <!-- Heading -->

            </div>

            <%
                Database db = new Database();
                Vector<Users> listOfUsers = db.retrieveAllCustomers();
            %>


            <div class="container">
                <div class="row">
                    <div class="four col-md-3">
                        <div class="counter-box "> <i class="fa fa-user"></i> <span class="counter"><%=listOfUsers.size()%></span>
                            <p>Total Customer</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter">3275</span>
                            <p>Profile</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter">289</span>
                            <p>Service</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter">1563</span>
                            <p>Test</p>
                        </div>
                    </div>


                </div>
            </div>



            <!-- Table with panel -->
            <div class="card card-cascade narrower" style="margin-top: 50px">

                <!--Card image-->
                <div class="view view-cascade gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center rounded text-center">
                    <p href="" class="white-text mx-3">Customers</p>
                </div>
                <!--/Card image-->

                <div class="px-4">

                    <div class="table-wrapper">
                        <!--Table-->
                        <table class="table table-hover mb-0">

                            <!--Table head-->
                            <thead>
                                <tr>
                                    <th>
                                        #
                                    </th>
                                    <th class="th-lg">
                                        Name
                                    </th>
                                    <th class="th-lg">
                                        NID
                                    </th>
                                    <th class="th-lg">
                                        Dial Number	
                                    </th>
                                    <th class="th-lg">
                                        Address
                                    </th>
                                    <th class="th-lg">
                                        Email
                                    </th>
                                    <th class="th-lg">
                                        Profile
                                    </th>
                                </tr>
                            </thead>
                            <!--Table head-->

                            <!--Table body-->
                            <tbody>
                                <%
                                    for (Users u : listOfUsers) {
                                %>
                                <tr>
                                    <th scope="row"><%=u.getuId()%></th>
                                    <td><%=u.getName()%></td>
                                    <td><%=u.getNid()%></td>
                                    <td><%=u.getDialNumber()%></td>
                                    <td><%=u.getAddress()%></td>
                                    <td><%=u.getEmail()%></td>
                                    <td><%=u.getProfile()%></td>
                                </tr>

                                <%}%> 

                            </tbody>
                            <!--Table body-->
                        </table>
                        <!--Table-->
                    </div>

                </div>

            </div>
            <!-- Table with panel -->




        </main>
        <!--Main layout-->







        <!--Footer-->
        <footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">



            <hr class="my-4">

            <!-- Social icons -->
            <div class="pb-4">
                <a href="#" target="_blank">
                    <i class="fab fa-facebook-f mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-twitter mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-youtube mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-google-plus mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-dribbble mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-pinterest mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-github mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-codepen mr-3"></i>
                </a>
            </div>
            <!-- Social icons -->

            <!--Copyright-->
            <div class="footer-copyright py-3">
                © 2020 Copyright:
                <a href="#" target="_blank"> TELECOM TRACK - INTAKE 40 </a>
            </div>
            <!--/.Copyright-->

        </footer>
        <!--/.Footer-->

        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Initializations -->
        <script type="text/javascript">
            // Animations initialization
            new WOW().init();
        </script>



        <script>
            $(document).ready(function () {

                $('.counter').each(function () {
                    $(this).prop('Counter', 0).animate({
                        Counter: $(this).text()
                    }, {
                        duration: 4000,
                        easing: 'swing',
                        step: function (now) {
                            $(this).text(Math.ceil(now));
                        }
                    });
                });

            });
        </script>

    </body>

</html>
