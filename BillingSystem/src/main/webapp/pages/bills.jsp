<%-- 
    Document   : bills
    Created on : May 16, 2020, 11:29:45 AM
    Author     : moham
--%>

<%@page import="com.billingsystem.entities.Customer"%>
<%@page import="com.billingsystem.daos.CustomerDAO"%>
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
                            <span>Add Customer</span>
                        </h4>

                        <form class="needs-validation" action="./addCustomer.jsp" method="POST" novalidate>
                            <input type="hidden" name="customer" value="addCustomer">
                            <button class="btn btn-primary btn-sm" type="submit">Add Customer</button>
                        </form>
                    </div>
                </div>
                <!-- Heading -->
            </div>

            <%
                CustomerDAO cd = new CustomerDAO();
                ArrayList<Customer> listOfCustomer = cd.getAll();
            %>

            <!-- Table with panel -->
            <div class="card card-cascade narrower" style="margin-top: 50px">
                <!--Card image-->
                <div class="view view-cascade gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center rounded text-center">
                    <p href="" class="white-text mx-3">Customers</p>
                </div>
                <!--/Card image-->
                <div class="px-4">
                    <div class="table-responsive">
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
                                    <th class="th-lg">
                                        Billing Date
                                    </th>
                                    <th class="th-lg">
                                        Edit
                                    </th>
                                    <th class="th-lg">
                                        Delete
                                    </th>
                                </tr>
                            </thead>
                            <!--Table head-->
                            <!--Table body-->
                            <tbody>
                                <%
                                    for (Customer c : listOfCustomer) {
                                %>
                                <tr>
                                    <th scope="row"><%=c.getId()%></th>
                                    <td><%=c.getName()%></td>
                                    <td><%=c.getNid()%></td>
                                    <td><%=c.getPhone()%></td>
                                    <td><%=c.getAddress()%></td>
                                    <td><%=c.getEmail()%></td>
                                    <td><%=c.getRatePlan().getName()%></td>
                                    <td><%=c.getBillingDate()%></td>
                                    <td>
                                        <form class="needs-validation" action="./addCustomer.jsp" method="POST" novalidate>
                                            <input type="hidden" name="customer" value="edtiCustomer">
                                            <input type="hidden" name="customerId" value=<%=c.getId()%>>
                                            <button class="btn btn-primary btn-sm" type="submit">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="needs-validation" action="../addCustomer" method="POST" novalidate>
                                            <input type="hidden" name="customer" value="deleteCustomer">
                                            <input type="hidden" name="customerId" value=<%=c.getId()%>>
                                            <button class="btn btn-primary btn-sm" type="submit">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                                <% }%> 
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
