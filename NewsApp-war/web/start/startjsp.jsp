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
        <title>TODO supply a title</title>
        <link rel="stylesheet" type="text/css" href="start/startcss.css">
        <script src="start/startjs.js"></script> 

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
                                <span class="label label-success bg-success">10</span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <ul class="dropdown-menu-over list-unstyled">
                                    <li class="header-ul text-center">You have 4 messages</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu list-unstyled">
                                            <li><!-- start message -->
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle  " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Support Team
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <!-- end message -->
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        AdminLTE Design Team
                                                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Developers
                                                        <small><i class="fa fa-clock-o"></i> Today</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Sales Department
                                                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://via.placeholder.com/160x160" class="rounded-circle " alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Reviewers
                                                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                    </h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer-ul text-center"><a href="#">See All Messages</a></li>
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
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-red"></i> 5 new members joined
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-user text-red"></i> You changed your username
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer-ul text-center"><a href="#">View all</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
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
                                <li class="active"><a href="#">CSS3 Animation</a></li>
                                <li><a href="#">Show your baskets</a></li>
                                <li><a href="#">Add new basket</a></li>
                                <li><a href="#">Switch basket</a></li>
                            </ul>
                        </li>
                        <li> <a href="#" data-toggle="collapse" data-target="#products" class="collapsed active" > <i class="fa fa-bar-chart-o"></i> <span class="nav-label">Bank Accounts</span> <span class="fa fa-chevron-left pull-right"></span> </a>
                            <ul class="sub-menu collapse" id="products">
                                <li class="active"><a href="#">CSS3 Animation</a></li>
                                <li><a href="#">Show accounts</a></li>
                                <li><a href="#">Set new account</a></li>
                                <li><a href="#">Create new account</a></li>
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
                        <li> <a href="#" data-toggle="collapse" data-target="#e-commerce" class="collapsed active" ><i class="fa fa-shopping-cart"></i> <span class="nav-label">Items</span><span class="fa fa-chevron-left pull-right"></span></a>
                            <ul  class="sub-menu collapse" id="e-commerce" >
                                <li><a href=""> All Items</a></li>
                                <li><a href=""> Your Items</a></li>
                                <li><a href="https://www.ebay.com/"> New offer </a></li>
                            </ul>
                        </li>
                        <li> <a href="/NewsApp-war/UserLogout"><i class="fa fa-warning"></i>                               
                                <span class="nav-label">Log out</span> 
                            </a></li>
                        <li> <i class="fa fa-files-o"></i> 
                            <div style="width:10px;height:1000px;"></div>
                        </li>
                    </ul>
                </div>
            </aside>
        </div>
    </body>
</html>

