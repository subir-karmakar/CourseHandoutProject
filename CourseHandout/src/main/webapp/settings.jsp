<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyApp</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Includes Sidebar -->
		<%@ include file="sidebar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Includes Header -->
				<%@ include file="header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Change Password</h1>
                    <div class="text-center">
                                    <span class="small text-danger">
                                    <% 
    									String user = (String)request.getAttribute("obj");
    									if(user=="updated"){
											out.print("Your password has been successfully updated!");
    									//verificationStatus="valid";
	
    									}
									%></span>
                                    </div>
                                    <br>
                    <form method="post" action="editSetting">
                        <div class="form-group row">
                            <div class="col-sm-6">
                            	<!-- <input type="hidden" name="id" id="id" value="${suser.getId()}"> -->
                            	<input type="password" class="form-control form-control-user"
                                            id="password" name="password" placeholder="Enter Your New Password" onkeyup="checkPass();">
                            </div>
                            <div class="col-sm-6">
                            	<input type="password" class="form-control form-control-user"
                                            id="repeatPassword" placeholder="Repeat Your New Password" onkeyup="checkPass();">
                            </div>
                        
                        </div>
                        <div class="form-group text-center">
                                	<span class="small text-danger" id="confirm-message"></span>
                                </div>
                        <div class="form-group text-right">
                            <input class="btn btn-primary" type="submit" value="Update Password">
                        </div>
                    </form>
                    <br>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Smart Endeavour 2023</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="/logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>
    <script type="text/javascript">
function checkPass()
{
    //Store the password field objects into variables ...
    var password = document.getElementById('password');
    var confirm  = document.getElementById('repeatPassword');
    //Store the Confirmation Message Object ...
    var message = document.getElementById('confirm-message');
    //Compare the values in the password field 
    //and the confirmation field
    if(password.value == confirm.value && password.value != ''){
        //The passwords match. 
        message.innerHTML             = 'Passwords Match!';
    }
    else if(password.value == '' && confirm.value== ''){
    	//The password fields are empty
        message.innerHTML             = '';
    }
    else{
        //The passwords do not match
        message.innerHTML             = 'Passwords Do Not Match!';
    }
}  
</script>

</body>

</html>