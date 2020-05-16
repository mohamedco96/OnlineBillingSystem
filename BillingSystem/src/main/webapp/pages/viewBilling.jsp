
<%@page import="com.billingsystem.messaging.email"%>
<%@page import="com.billingsystem.messaging.nxSms"%>
<%@page import="com.billingsystem.entities.RatePlan"%>
<%@page import="com.billingsystem.entities.Service"%>
<%@page import="com.billingsystem.entities.Customer"%>
<%@page import="com.billingsystem.daos.DetailedBillDao"%>
<%@page import="com.billingsystem.entities.DetailedBill"%>
<%-- 
    Document   : viewBilling
    Created on : May 1, 2020, 4:52:01 PM
    Author     : moham
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>

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
        <link href="../css/bill.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <%
        DetailedBill detailedBill;
        DetailedBillDao detailedBillDao = new DetailedBillDao();
        Customer customer;
        Service service;
        detailedBill = detailedBillDao.get("11221234567", 4);
        customer = detailedBill.getCustomer();
        DetailedBill.RecService recService;
        DetailedBill.BillServices billServices;
        Vector<DetailedBill.RecService> vec2 = detailedBill.getVec2();
        Vector<DetailedBill.BillServices> vec = detailedBill.getVec();
    %>
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
                            <a href="../dashboard.jsp" target="_blank">Dashboard</a>
                            <span>/</span>
                            <span>View Billing</span>
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
            <button type="button" class="btn btn-outline-primary waves-effect rounded " onclick="HTMLtoPDF()"> Pdf Generator </button>
            <%
                String name=detailedBill.getCustomer().getName();
                    String total=String.valueOf(detailedBill.getTotal());
                    String text="Hi "+name+" Youre Total Invoice is "+total;
                if (request.getParameter("email") != null) {
                    email e = new email();
                    e.sendMail(request.getParameter("email"),text);
                    System.out.println(request.getParameter("email"));
                }

                if (request.getParameter("phone") != null) {
                    nxSms sms = new nxSms();
                    sms.sendSMS(request.getParameter("phone"),text);
                    System.out.println(request.getParameter("phone"));
                }
            %>
            <div id="invoice" class="effect2">
                <div id="invoice-top">
                    <div class="logo"></div>
                    <div class="info">
                        <h2>MDB Company</h2>
                        <p> Billing System </br>
                        </p>
                    </div><!--End Info-->
                    <div class="title">
                        <h1>Invoice #01</h1>
                        <p id="billDate">
                            Payment Due: </br>
                        </p>
                    </div><!--End Title-->
                </div><!--End InvoiceTop-->
                <div id="invoice-mid">
                    <div class="clientlogo"></div>
                    <div class="info">
                        <h2><%=detailedBill.getCustomer().getName()%></h2>
                        <p><%=detailedBill.getCustomer().getEmail()%></p>
                        <br>
                        <p> <%=detailedBill.getCustomer().getPhone()%></p>

                        <br>
                    </div>
                </div><!--End Invoice Mid-->
                <div id="invoice-bot">
                    <div id="table">
                        <table>
                            <tr class="tabletitle">
                                <td class="item"><h2>Services</h2></td>
                                <td class="Hours"><h2>Usage</h2></td>
                                <td class="Rate"><h2>Rate</h2></td>
                                <td class="subtotal"><h2>Cost</h2></td>
                            </tr>
                            <% for (int i = 0; i < vec.size(); i++) {%>
                            <tr class="service">

                                <td class="tableitem"><p class="itemtext"><%=vec.get(i).getSvc().getName()%></p></td>
                                <td class="tableitem"><p class="itemtext"><%=vec.get(i).getUsed()%></p></td>
                                <td class="tableitem"><p class="itemtext"><%=vec.get(i).getRate()%></p></td>
                                <td class="tableitem"><p class="itemtext"><%=vec.get(i).getCost()%></p></td>
                            </tr>
                            <% } %>
                            <% for (int i = 0; i < vec2.size(); i++) {%>
                            <tr class="service">

                                <td class="tableitem"><p class="itemtext"><%=vec2.get(i).getSvc().getName()%></p></td>
                                <td class="tableitem"><p class="itemtext"><%=vec2.get(i).getCost()%></p></td>
                            </tr>
                            <% }%>
                            <tr class="tabletitle">
                                <td></td>
                                <td></td>
                                <td class="Rate"><h2>Total</h2></td>
                                <td class="payment"><h2><%=detailedBill.getTotal()%></h2></td>
                            </tr>
                        </table>
                    </div><!--End Table-->
                </div><!--End InvoiceBot-->
            </div><!--End Invoice-->
            <div class="row">
                <div class="col">
                    <form class="needs-validation" action="./viewBilling.jsp" method="POST" novalidate>
                        <div class="form-row">
                            <div class="col">
                                <input type="text" class="form-control" id="validationCustom01" placeholder="Enter Email" 
                                       required name="email">
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                            <div class="col">
                                <button class="btn btn-primary btn-sm" type="submit">Send Email</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col">
                    <form class="needs-validation" action="./viewBilling.jsp" method="POST" novalidate>
                        <div class="form-row">
                            <div class="col">
                                <input type="text" class="form-control" id="validationCustom01" placeholder="Enter Phone Number" 
                                       required name="phone">
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                            <div class="col">
                                <button class="btn btn-primary btn-sm" type="submit">Send SMS</button>
                            </div>
                        </div>
                    </form>
                </div>
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
        <script src="../js/jspdf.js"></script>
        <script src="../js/jquery-2.1.3.js"></script>
        <script src="../js/pdfFromHTML.js"></script>
        <script>
                var dt = new Date();
                document.getElementById("billDate").innerHTML = (("0" + dt.getDate()).slice(-2)) + "." + (("0" + (dt.getMonth() + 1)).slice(-2)) + "." + (dt.getFullYear()) + " " + (("0" + dt.getHours()).slice(-2)) + ":" + (("0" + dt.getMinutes()).slice(-2));</script>
    </body>
</html>