<%-- 
    Document   : dashboard
    Created on : May 1, 2020, 3:03:27 PM
    Author     : moham
--%>
<%@page import="com.billingsystem.entities.RatePlan"%>
<%@page import="com.billingsystem.daos.RatePlanDAO"%>
<%@page import="com.billingsystem.daos.ServiceDAO"%>
<%@page import="com.billingsystem.entities.Service"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.billingsystem.entities.Customer"%>
<%@page import="com.billingsystem.daos.CustomerDAO"%>
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
                    <a href="index.jsp" class="list-group-item active waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Dashboard
                    </a>
                    <a href="./pages/service.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-robot mr-3"></i>Services</a>
                    <a href="./pages/timePackage.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-clock mr-3"></i>Time Package</a>
                    <a href="./pages/tarrifZone.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-coins mr-3"></i>Tarrif Zone</a>
                    <a href="./pages/ratePlan.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-box mr-3"></i>Rate plan</a>
                    <a href="./pages/addCustomer.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-user mr-3"></i>Customers</a>
                    <a href="./pages/viewBilling.jsp" class="list-group-item list-group-item-action waves-effect">
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
                            <a href="dashboard.jsp" target="_blank">Dashboard</a>
                        </h4>
                        <form class="d-flex justify-content-center" action="./pages/searchResult.jsp" method="GET">
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
                // Database db = new Database();
                // Vector<Users> listOfUsers = db.retrieveAllCustomers();
                CustomerDAO cd = new CustomerDAO();
                ServiceDAO sd = new ServiceDAO();
                RatePlanDAO rpd = new RatePlanDAO();
                ArrayList<Customer> listOfCustomer = cd.getAll();
                ArrayList<Service> listOfService = sd.getAll();
                ArrayList<RatePlan> listOfRatePlan = rpd.getAll();
            %>
            <div class="container">
                <div class="row">
                    <div class="four col-md-3">
                        <div class="counter-box "> <i class="fa fa-user"></i> <span class="counter"><%=listOfCustomer.size()%></span>
                            <p>Total Customer</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter"><%=listOfService.size()%></span>
                            <p>Service</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter"><%=listOfRatePlan.size()%></span>
                            <p>Rate Plan</p>
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
                                    <th class="th-lg">
                                        Billing Date
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
            <!---------------------------------- Services Section  ------------------------------------->
            <div class="container my-5">
                <!-- Section -->
                <section>
                    <h3 class="font-weight-bold text-center dark-grey-text pb-2">Services</h3>
                    <hr class="w-header my-4">
                    <div class="row">
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card text-center bg-success text-white">
                                <div class="card-body">
                                    <p class="mt-4 pt-2"><i class="far fa-object-ungroup fa-4x"></i></p>
                                    <h5 class="font-weight-normal my-4 py-2"><a class="text-white" href="#">Services 1</a></h5>
                                    <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit, error amet numquam iure provident voluptate esse quasi, veritatis totam voluptas nostrum quisquam eum porro a pariatur veniam..</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card text-center">
                                <div class="card-body">
                                    <p class="mt-4 pt-2"><i class="fas fa-mobile-alt fa-4x grey-text"></i></p>
                                    <h5 class="font-weight-normal my-4 py-2"><a class="dark-grey-text" href="#">Services 2</a></h5>
                                    <p class="text-muted mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit, error amet numquam iure provident voluptate esse quasi, veritatis totam voluptas nostrum quisquam eum porro a pariatur veniam..</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3 mb-4">
                            <div class="card text-center deep-purple lighten-1 text-white">
                                <div class="card-body">
                                    <p class="mt-4 pt-2"><i class="fas fa-chart-line fa-4x"></i></p>
                                    <h5 class="font-weight-normal my-4 py-2"><a class="text-white" href="#">Services 3</a></h5>
                                    <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit, error amet numquam iure provident voluptate esse quasi, veritatis totam voluptas nostrum quisquam eum porro a pariatur veniam..</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-3">
                            <div class="card text-center">
                                <div class="card-body">
                                    <p class="mt-4 pt-2"><i class="fas fa-bullhorn fa-4x grey-text"></i></p>
                                    <h5 class="font-weight-normal my-4 py-2"><a class="dark-grey-text" href="#">Services 4</a></h5>
                                    <p class="text-muted mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit, error amet numquam iure provident voluptate esse quasi, veritatis totam voluptas nostrum quisquam eum porro a pariatur veniam..</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- Section -->
            </div>
            <!---------------------------------- Pricing Section  ------------------------------------->
            <div class="container my-5">
                <!--Section: Content-->
                <section class="text-center dark-grey-text">
                    <!-- Section heading -->
                    <h3 class="font-weight-bold pb-2 mb-4">Our pricing plans</h3>
                    <!-- Grid row -->
                    <div class="row">
                        <!-- Grid column -->
                        <div class="col-lg-4 col-md-12 mb-4">
                            <!-- Pricing card -->
                            <div class="card pricing-card">
                                <!-- Price -->
                                <div class="price header white-text blue rounded-top">
                                    <h2 class="number">100 LE</h2>
                                    <div class="version">
                                        <h5 class="mb-0">Basic</h5>
                                    </div>
                                </div>
                                <!-- Features -->
                                <div class="card-body striped mb-1">
                                    <ul>
                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Features -->
                            </div>
                            <!-- Pricing card -->
                        </div>
                        <!-- Grid column -->
                        <!-- Grid column -->
                        <div class="col-lg-4 col-md-6 mb-4">
                            <!-- Pricing card -->
                            <div class="card pricing-card">
                                <!-- Price -->
                                <div class="price header white-text indigo rounded-top">
                                    <h2 class="number">200 LE</h2>
                                    <div class="version">
                                        <h5 class="mb-0">Pro</h5>
                                    </div>
                                </div>
                                <!-- Features -->
                                <div class="card-body striped mb-1">
                                    <ul>
                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Features -->
                            </div>
                            <!-- Pricing card -->
                        </div>
                        <!-- Grid column -->
                        <!-- Grid column -->
                        <div class="col-lg-4 col-md-6 mb-4">
                            <!-- Pricing card -->
                            <div class="card pricing-card">
                                <!-- Price -->
                                <div class="price header white-text deep-purple rounded-top">
                                    <h2 class="number">300 LE</h2>
                                    <div class="version">
                                        <h5 class="mb-0">Enterprise</h5>
                                    </div>
                                </div>
                                <!-- Features -->
                                <div class="card-body striped mb-1">
                                    <ul>
                                        <li>
                                            <p class="mt-2"><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-check green-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                        <li>
                                            <p><i class="fas fa-times red-text pr-2"></i>Lorem ipsum dolor sit amet</p>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Features -->
                            </div>
                            <!-- Pricing card -->
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                </section>
                <!--Section: Content-->
            </div>
            <!---------------------------------- Team Section  ------------------------------------->
            <div class="container py-5 my-5 z-depth-1">
                <section class="p-md-3 mx-md-5">
                    <h2 class="text-center mx-auto font-weight-bold mb-5 pb-2">Our Team</h2>
                    <div class="row">
                        <div class="col-lg-3 col-md-6 mb-lg-0 mb-4">
                            <div class="avatar white d-flex justify-content-center align-items-center">
                                <img
                                    src="./img/rawy.jpg"
                                    class="img-fluid z-depth-1"
                                    />
                            </div>
                            <div class="text-center mt-4">
                                <h5 class="font-weight-bold pb-1">Mostaf El Rawy</h5>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 mb-lg-0 mb-4">
                            <div class="avatar white d-flex justify-content-center align-items-center">
                                <img
                                    src="./img/hakim.jpg"
                                    class="img-fluid z-depth-1"
                                    />
                            </div>
                            <div class="text-center mt-4">
                                <h5 class="font-weight-bold pb-1">Mahmoud Abd El-hakim</h5>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 mb-md-0 mb-4">
                            <div class="avatar white d-flex justify-content-center align-items-center">
                                <img
                                    src="./img/salah.jpg"
                                    class="img-fluid z-depth-1"
                                    />
                            </div>
                            <div class="text-center mt-4">
                                <h5 class="font-weight-bold pb-1">Ahmed Salah</h5>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 mb-md-0 mb-4">
                            <div class="avatar white d-flex justify-content-center align-items-center">
                                <img
                                    src="./img/mohamed.jpg"
                                    class="img-fluid z-depth-1"
                                    />
                            </div>
                            <div class="text-center mt-4">
                                <h5 class="font-weight-bold pb-1">Mohamed Ibrahim</h5>
                            </div>
                        </div>
                    </div>
                </section>
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