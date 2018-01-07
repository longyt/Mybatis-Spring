<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/4
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student jsp</title>
    <script type="text/javascript" src="bootstrap/jquery.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <div id="StudentBody" class="container">
        <div>
            <select v-model="searchType" class="selectpicker form-control" style="width: 110px;float: left;margin-left: 115px;">
                <option value="">你的选项</option>

                <option value="sid">编号</option>

                <option value="age">年龄</option>

                <option value="sex">性别</option>

                <option value="sname">姓名</option>

            </select>
            <input v-model="searchText"  type="text" class="form-control" placeholder="搜索" style="width: 200px;float: left;margin-left: 100px"/>
            <button v-on:click="querylist" class=" btn btn-primary" value="搜索">搜索</button>
            <button class=" btn btn-danger" value="增加"  v-on:click="insertStudent">增加</button>
            <a href="/test.action">test</a>
        </div>

        <table align="center" width="80%" border="1px" style="margin-top: 20px">
            <tr>
                <td align="center">编号</td>
                <td align="center">年龄</td>
                <td align="center">性别</td>
                <td align="center">姓名</td>
                <td align="center">操作</td>
            </tr>
            <tr v-for="row in StudnetTable.RowData">
                <td align="center">{{row.sid}}</td>
                <td align="center">{{row.age}}</td>
                <td align="center">{{row.sex}}</td>
                <td align="center">{{row.sname}}</td>
                <td align="center">
                    <button class="btn btn-primary" v-on:click="updateStudent(row)">修改</button>
                    <button class="btn btn-danger" v-on:click="deletStudent(row)">删除</button>
                </td>
            </tr>
        </table>
        <!--modal  start-->
        <div class="modal fade" tabindex="-1" role="dialog" id="StudentModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label class="control-label">年龄：</label>
                                <select class="form-control" v-model="student.age">
                                    <option v-for="sage in 120" v-bind:value="sage">{{sage}}</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">姓名：</label>
                                <input type="text" class="form-control" v-model="student.sname"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">性别</label>
                                <select class="form-control" v-model="student.sex">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" v-on:click="save">提交</button>
                    </div>
                </div>
            </div>
        </div>
        <!--modal  end-->
    </div>


</body>
<script type="text/javascript" src="bootstrap/vue.js"></script>
<script type="text/javascript">
    var jk;
    var vue=new Vue({
        el:"#StudentBody",
        data:{
            StudnetTable:{RowData:[]},
            student:{},
            SearchStudent:{},
            DataLength:"",
            searchType:"",
            searchText:""

        },
        <!-- methods  end -->
        methods:{
            querylist:function () {
                var this_ = this;
                this_.SearchStudent={};
                if(this_.searchType!=""){
                    this_.SearchStudent[this_.searchType]=this_.searchText;
                }else{
                    this_.SearchStudent["sid"]="";
                }
                console.log(this_.SearchStudent);
                $.ajax({
                    url:"/getAllStudent.action",
                    dataType:"json",
                    type:"post",
                    contentType: "application/json",
                    data:JSON.stringify(this_.SearchStudent),
                    success:function (data) {
                        console.log(JSON.stringify(data));
                        this_.StudnetTable.RowData=data;
                        this_.DataLength=data.length+1;
                    }
                })
            },
            deletStudent:function (row) {
                var this_ = this;
                $.ajax({
                   url:"/deleteStudent.action",
                   dataType:"json",
                   type:"post",
                   data:{studentid:row.sid}
                })
                this_.querylist();
            },
            insertStudent:function () {
                this.showModal();
                this.student={};
               jk="insert";
            },
            save:function () {
                var this_ = this;
                if(jk=="insert"){
                    //this_.student["sid"]=this_.DataLength;
                    console.log(JSON.stringify(this_.student))
                    $.ajax({
                        url:"/insertStudent.action",
                        type:"post",
                        dataType:"json",
                        contentType: "application/json",
                        data:JSON.stringify(this_.student),
                        success:function () {

                        }
                    })
                }else if(jk=="update"){
                    $.ajax({
                        url:"/updateStudent.action",
                        type:"post",
                        dataType:"json",
                        contentType: "application/json",
                        data:JSON.stringify(this_.student),
                        success:function () {
                            
                        }
                    })
                }
                //console.log(JSON.stringify(this_.student));
                this_.hideModal();
            },
            updateStudent:function (row) {
                jk="update";
                var this_ = this;
                $.ajax({
                    url:"/queryoneStudent.action",
                    type:"post",
                    dataType:"json",
                    data:{id:row.sid},
                    success:function (data) {
                        this_.student=data;
                        //console.log(JSON.stringify(this_.student));
                    }
                })

                this_.showModal();
            },
            searchStudent:function () {
                var this_=this;
                this_.SearchStudent[this_.searchType]=this_.searchText;
                console.log(JSON.stringify(this_.SearchStudent))
                $.ajax({
                    url:"SearchStudent.action",
                    type:"post",
                    dataType:"json",
                    contentType: "application/json",
                    data:JSON.stringify(this_.SearchStudent),
                    success:function (data) {
                        console.log(data);
                        this_.StudnetTable.RowData=data;
                    }
                })
            },
            showModal:function(){
                $("#StudentModal").modal("show");
            },
            hideModal:function(){
                $("#StudentModal").modal("hide");
            },
        },
        <!-- methods  end -->
        created:function () {
            this.querylist();
        }
    })


</script>
</html>
