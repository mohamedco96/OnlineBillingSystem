<%-- 
    Document   : ratePlan
    Created on : May 3, 2020, 10:54:55 PM
    Author     : moham
--%>

<%@page import="java.util.List"%>
<%@page import="com.billingsystem.entities.ServicePackage"%>
<%@page import="com.billingsystem.entities.RatePlan"%>
<%@page import="com.billingsystem.daos.RatePlanDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="../css/mdb.min.css" rel="stylesheet">
        <link href="../css/style.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <style>
        .pt-5,
        .py-5 {
            margin-bottom: 100px;
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
                    <a href="../index.jsp" class="list-group-item active waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Dashboard
                    </a>
                    <a href="service.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-robot mr-3"></i>Services</a>
                    <a href="timePackage.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-clock mr-3"></i>Time Package</a>
                    <a href="tarrifZone.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-coins mr-3"></i>Tarrif Zone</a>
                    <a href="ratePlan.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-box mr-3"></i>Rate plan</a>
                    <a href="customers.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-user mr-3"></i>Customers</a>
                    <a href="viewBilling.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-file-invoice mr-3"></i>Billing</a>

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
                            <a href="../index.jsp" target="_blank">Dashboard</a>
                            <span>/</span>
                            <span>Rate Plan</span>
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
                RatePlanDAO rpd = new RatePlanDAO();
                ArrayList<RatePlan> allRatePlan = rpd.getAll();

//                ArrayList<ServicePackage> allServicePackage = rpd.getAllServicePackage();
            %>


            <div class="container my-5">
                <!--Section: Content-->
                <section class="text-center dark-grey-text">
                    <!-- Section heading -->
                    <h3 class="font-weight-bold pb-2 mb-4">Our pricing plans</h3>

                    <a class="btn btn-primary" href="./RatePlanAndServicePackage.jsp">Add Rate Plan</a>
                    <!-- Grid row -->
                    <div class="row">
                        <% for (int i = 0; i < allRatePlan.size(); i++) {%>
                        <div class="col-lg-4 col-md-12 mb-4">
                            <!-- Pricing card -->
                            <div class="card pricing-card">
                                <!-- Price -->
                                <div class="price header white-text blue rounded-top">
                                    <h2 class="number"><%=allRatePlan.get(i).getMonthlyFees()%></h2>
                                    <div class="version">
                                        <h5 class="mb-0"><%=allRatePlan.get(i).getName()%></h5>
                                    </div>
                                </div>
                                <!-- Features -->
                                <div class="card-body striped mb-1">
                                    <ul>
                                        <%
                                            ServicePackage sp = new ServicePackage();
                                            RatePlanDAO dsj = new RatePlanDAO();

                                            List<ServicePackage> allServicePackage = allRatePlan.get(i).getServicePackages();
                                            for (int j = 0; j < allServicePackage.size(); j++) {

                                        %>

                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i><%= allServicePackage.get(j).getService().getName()%></p>
                                        </li>
                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i>Free Units:<%= allServicePackage.get(j).getFree_units()%></p>
                                        </li>
                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i>Rate:<%= allServicePackage.get(j).getRate()%></p>
                                        </li>
                                        <%}%>
                                    </ul>
                                    <a class="btn btn-primary">Edit</a>
                                    <form class="needs-validation" action="./addCustomer.jsp" method="POST" novalidate>
                                        <input type="hidden" name="customer" value="addCustomer">
                                        <button class="btn btn-primary btn-sm" type="submit">Delete</button>
                                    </form>
                                </div>
                                <!-- Features -->

                            </div>
                            <!-- Pricing card -->
                        </div>
                        <%}%>
                    </div>
                    <!-- Grid row -->
                </section>
                <!--Section: Content-->
            </div>


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
                TELECOM TRACK - INTAKE 40
            </div>
            <!--/.Copyright-->
        </footer>
        <!--/.Footer-->
        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="../js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="../js/mdb.min.js"></script>
        <!-- Initializations -->
        <script type="text/javascript">
            // Animations initialization
            new WOW().init();
        </script>
        <script src="../js/RatePlan.js"></script>


    </body>
</html>