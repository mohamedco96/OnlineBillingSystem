<%-- 
    Document   : addCustomer
    Created on : Apr 30, 2020, 3:22:50 PM
    Author     : moham
--%>

<%@page import="com.billingsystem.entities.Service"%>
<%@page import="com.billingsystem.daos.ServiceDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.billingsystem.entities.RatePlan"%>
<%@page import="com.billingsystem.daos.RatePlanDAO"%>
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
    <body class="grey lighten-3">
        <!--Main Navigation-->
        <header>
            <!-- Sidebar -->
            <div class="sidebar-fixed position-fixed">
                <a class="logo-wrapper waves-effect">
                    <img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">
                </a>
                <div class="list-group list-group-flush">
                    <a href="../dashboard.jsp" class="list-group-item active waves-effect">
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
                    <a href="ServicePackage.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Service Package</a>
                    <a href="addCustomer.jsp" class="list-group-item list-group-item-action waves-effect">
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
                            <a href="../dashboard.jsp" target="_blank">Dashboard</a>
                            <span>/</span>
                            <span>Add Customer</span>
                        </h4>
                    </div>
                </div>
                <!-- Heading -->
            </div>
            <div class="container" style="margin-top: 50px;">
                <h3 style="margin-bottom: 20px">Customer Profile</h3>
                <form class="needs-validation" action="../addCustomer" method="POST" novalidate>
                    <div class="form-row">
                        <input type="text" class="form-control mb-4" id="validationCustom01" placeholder="Full name" name="name" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <input type="text" class="form-control mb-4" id="validationCustom02" placeholder="National ID" name="nid" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <input type="text" class="form-control mb-4" id="validationCustom02" placeholder="Enter Dial Number" name="dnum" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div class="form-row">
                        <input type="text" class="form-control mb-4" id="validationCustom03" placeholder="Enter customer Address" name="addr" required>
                        <div class="invalid-feedback">
                            Please provide a valid Address.
                        </div>
                        <input type="email" class="form-control mb-4" id="validationCustom04" placeholder="Enter customer Email" name="email" required>
                        <div class="invalid-feedback">
                            Please provide a valid Email.
                        </div>
                        <input type="date" class="form-control mb-4" id="validationCustom04"  name="billing_date" required>
                        <div class="invalid-feedback">
                            Please provide a Date.
                        </div>
                        <label>Profile</label>
                        <%
                            RatePlanDAO rpd = new RatePlanDAO();
                            ArrayList<RatePlan> allRatePlan = rpd.getAll();
                            
                            ServiceDAO sd = new ServiceDAO();
                            ArrayList<Service> AllRecurringServices = sd.getAllRecurringServices();
                            ArrayList<Service> AllOneTimeFee = sd.getAllOneTimeFee();
                        %>


                        <select class="browser-default custom-select mb-4" name="profile">
                            <option selected>select Profile from menu</option>
                            <%
                                for (int i = 0; i < allRatePlan.size(); i++) {
                            %>
                            <option value="<%=allRatePlan.get(i).getId()%>"><%=allRatePlan.get(i).getName()%></option>
                            <%}%>
                        </select>

                        <label>Recurring Services</label>
                        <select class="browser-default custom-select mb-4" name="profile">
                            <option selected>select Recurring Services from menu</option>
                            <%
                                for (int i = 0; i < AllRecurringServices.size(); i++) {
                            %>
                            <option value="<%=AllRecurringServices.get(i).getId()%>"><%=AllRecurringServices.get(i).getName()%></option>
                            <%}%>
                        </select>
                        <label>One time fee</label>
                        <select class="browser-default custom-select mb-4" name="profile">
                            <option selected>select One time fee from menu</option>
                            <%
                                for (int i = 0; i < AllOneTimeFee.size(); i++) {
                            %>
                            <option value="<%=AllOneTimeFee.get(i).getId()%>"><%=AllOneTimeFee.get(i).getName()%></option>
                            <%}%>
                        </select>
                    </div>
                    <button class="btn btn-primary btn-sm" type="submit">Submit</button>
                </form>
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
        <script>
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </body>
</html>