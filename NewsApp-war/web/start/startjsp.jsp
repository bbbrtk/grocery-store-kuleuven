<!--
    Author     : Bartosz Sobkowiak
    University : KU Leuven, Belgium
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Start</title>
        <link rel="stylesheet" type="text/css" href="/NewsApp-war/start/startcss.css">
        <link rel="stylesheet" type="text/css" href="/NewsApp-war/login/logincss.css">
        <script src="/NewsApp-war/start/startjs.js"></script> 

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    </head>
    <body>
        <header class="header">
            <nav class="navbar navbar-toggleable-md navbar-light pt-0 pb-0 ">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="float-left"> <a href="#" class="button-left"><span class="fa fa-fw fa-bars "></span></a> </div>
                <div class="collapse navbar-collapse flex-row-reverse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown messages-menu">
                            <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-bell-o"></i>
                                <span class="label label-success bg-success"><%= request.getAttribute("timerStatus")%></span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <ul class="dropdown-menu-over list-unstyled">
                                    <li class="header-ul text-center">Session time status</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu list-unstyled">
                                            <li><!-- start message -->
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle  " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        <%= request.getAttribute("timerStatus")%> / 300 seconds
                                                        <small><i class="fa fa-clock-o"></i> Now </small>
                                                    </h4>
                                                    <p>Logout when time passes</p>
                                                </a>
                                            </li>
                                            <!-- end message -->
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Session started
                                                        <small><i class="fa fa-clock-o"></i>On startup</small>
                                                    </h4>
                                                    <p>Timer started</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer-ul text-center"><a href="#">Close</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item dropdown notifications-menu">
                            <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-envelope-o"></i>
                                <span class="label label-warning bg-warning">10</span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <ul class="dropdown-menu-over list-unstyled">
                                    <li class="header-ul text-center">You have 10 notifications</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu list-unstyled">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                                                    page and may cause design problems
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer-ul text-center"><a href="#">Close</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <table>
            <tr>
                <th>
            <div class="main">
                <aside>
                    <div class="sidebar left ">
                        <div class="user-panel">
                            <div class="pull-left image">
                                <img src="http://via.placeholder.com/160x160" class="rounded-circle" alt="User Image">
                            </div>
                            <div class="pull-left info">
                                <p> <%= request.getAttribute("userLogin")%></p>
                                <p> ID: <%= request.getAttribute("userId")%></p>
                                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                            </div>
                        </div>
                        <ul class="list-sidebar bg-defoult">
                            <li> <a href="#"><i class="fa fa-diamond"></i> <span class="nav-label">Buy item</span></a> </li>
                            <li> <a href="#" data-toggle="collapse" data-target="#dashboard" class="collapsed active" > <i class="fa fa-th-large"></i> <span class="nav-label"> Baskets </span> <span class="fa fa-chevron-left pull-right"></span> </a>
                                <ul class="sub-menu collapse" id="dashboard">
                                    <li><a href="#">Show your baskets</a></li>
                                    <li><a href="/NewsApp-war/addBankOrBasket">Add new basket</a></li>
                                    <li><a href="#">Switch basket</a></li>
                                </ul>
                            </li>
                            <li> <a href="#" data-toggle="collapse" data-target="#e-commerce" class="collapsed active" ><i class="fa fa-shopping-cart"></i> <span class="nav-label">Items</span><span class="fa fa-chevron-left pull-right"></span></a>
                                <ul  class="sub-menu collapse" id="e-commerce" >
                                    <li><a href=""> All Items</a></li>
                                    <li><a href=""> Your Items</a></li>
                                    <li><a href="https://www.ebay.com/"> New offer </a></li>
                                </ul>
                            </li>
                            <li> <a href="#" data-toggle="collapse" data-target="#products" class="collapsed active" > <i class="fa fa-bar-chart-o"></i> <span class="nav-label">Bank Accounts</span> <span class="fa fa-chevron-left pull-right"></span> </a>
                                <ul class="sub-menu collapse" id="products">
                                    <li><a href="#">Show accounts</a></li>
                                    <li><a href="#">Set new account</a></li>
                                    <li><a href="/NewsApp-war/addBankOrBasket">Create new account</a></li>
                                    <li><a href="http://bankbelgium.com/">Bank Belgium</a></li>
                                    <li><a href="https://www.kbc.com/en">KBC</a></li>
                                </ul>
                            </li>
                            <li> <a href="#" data-toggle="collapse" data-target="#tables" class="collapsed active" ><i class="fa fa-laptop"></i> <span class="nav-label">Settings</span><span class="fa fa-chevron-left pull-right"></span></a>
                                <ul  class="sub-menu collapse" id="tables" >
                                    <li><a href="/settings/settings.html"> Change Password</a></li>
                                    <li><a href="/settings/settings.html"> Change Login</a></li>
                                    <li><a href="/NewsApp-war/UserLogout"> Force Log Out</a></li>
                                </ul>
                            </li>
                            <li> <a href="/NewsApp-war/UserLogout"><i class="fa fa-warning"></i>                               
                                    <span class="nav-label">Log out</span> 
                                </a></li>
                            <li> <i class="fa fa-files-o"></i> 
                                <div style="width:10px;height:400px;"></div>
                            </li>
                        </ul>
                    </div>
                </aside>
            </div> 
        </th>
        <th>
        <div>
            <p style="width:300px" ></p>
        </div>
    </th>
    <th>
    <div>
        <p style="width:700px" ></p>
    </div>
</th>
</tr>
</table>


</body>
</html>

