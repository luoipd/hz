<!DOCTYPE html>

<html lang="en"> <!--<![endif]-->

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7;IE=edge,chrome=1">
    <title>权限后台</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/select2_metro.css"/>
    <link rel="stylesheet" href="${request.contextPath}/static/css/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${request.contextPath}/static/image/favicon.ico"/>
</head>

<#include "${request.contextPath}/common/header.ftl"/>
<div class="page-container row-fluid">
<#include "${request.contextPath}/common/sidebar.ftl"/>
</div>
<#include "${request.contextPath}/common/footer.ftl"/>

<#include "${request.contextPath}/admin/sysFunctionManager/addFunction.ftl"/>
<#include "${request.contextPath}/admin/sysFunctionManager/editFunction.ftl"/>

<script src="${request.contextPath}/static/sysFunctionManager/function-manager.js"></script>
<script>
    jQuery(document).ready(function () {

        App.init();
        FunctionManaged.initButton();


    });
    function iFrameHeight() {
        var ifm= document.getElementById("iframepage");
        var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
        if(ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight*0.6;//屏幕高度的60%
        }
    }
</script>
<!-- END BODY -->

</html>