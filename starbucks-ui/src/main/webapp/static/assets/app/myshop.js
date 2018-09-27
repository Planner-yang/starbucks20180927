var MyShop = function () {

    /**
     * 私有方法
     */
        //iCheck for checkbox and radio inputs
    var handlerInitICheck = function () {
        
            $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                checkboxClass: 'icheckbox_minimal-blue',
                radioClass: 'iradio_minimal-blue'
            })
        }

        
    var handlerInitDataTable = function (url,columns) {
        var dataTable = $('#dataTable').DataTable({
            paging: true,
            processing: true,
            serverSide: true,
            searching: false,
            ordering: false,
            lengthChange: false,
            pageLength: 10,
            ajax: {
                url: url,
                type: 'GET'
            },
            columns: columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            drawCallback: function (settings) {
                // 初始化 iCheck
                handlerInitICheck();

                // 全选
                $(".chk_master").iCheck("uncheck");
                $(".chk_master").on("ifClicked", function (event) {
                    if (!$(this).is(':checked')) {
                        $("#dataTable input[type='checkbox']").iCheck('check');

                    }

                    else {
                        $("#dataTable input[type='checkbox']").iCheck('uncheck');

                    }
                });



            }
        });

        bindEvent();
        return dataTable;

        /**
         * DataTables 相关事件绑定
         */
        function bindEvent() {
            // 重置 DataTables
            $("#btnDataTableReset").on("click", function () {
                window.location.reload();
            });
        }

    };

    var handlerModalAlert = function (message) {
        $('#modal-alert').modal("show");
        $('#modal-alert .modal-body p').html(message);
        $('#btnAlertOk').unbind();
        $('#btnAlertOk').bind('click', function () {
            $('#modal-alert').modal("hide");
        })

    }

    var handlerModalConfirm = function (message, callback) {
        $('#modal-confirm').modal("show");
        $('#modal-confirm .modal-body p').html(message);
        $('#btnConfirmOk').unbind();
        $('#btnConfirmOk').bind('click', function () {
            callback();
        })

    }

    var handlerShowDelete = function (url, message) {

        MyShop.modalconfirm(message == null ? "确定要删除该用户数据吗?" : message, function () {

            $('#modal-confirm').modal("hide");
            window.location.href = url;

        });
    }

    var handlerShowUpdate = function (url) {
        var _checkbox = $('input[type="checkbox"].chk_slave:checked');

        if (_checkbox.length < 1) {
           MyShop.modalalert("尚未选择任何数据项");
        }

        else if (_checkbox.length > 1) {
            MyShop.modalalert("不能选择超过一项用户数据");
        }

        else {
            var _id = _checkbox[0].id
            window.location.href = url + _id;
        }

    }

    var hanglerShowDeleteMulti = function (url, message) {
        var _checkbox = $('input[type="checkbox"].chk_slave:checked');

        if (_checkbox.length < 1) {
            MyShop.modalconfirm("尚未选择任何数据项", function () {
                $('#modal-confirm').modal("hide");
            });
        }

        else {
            MyShop.modalconfirm("确定要删除已选的数据项吗？", function () {
                var ids = new Array();
                $(_checkbox).each(function () {
                    ids.push($(this).attr("id"))
                });

                window.location.href = url + ids;
            });
        }
    }

    var handlerShowView = function (url) {

        $.ajax({
            url:url,
            cache:false,
            success: function(html){
               MyShop.modalalert(html)
            }

        })
    }

    var handlerShowZTree = function (url) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                type: "get",
                url: url,
                autoParam: ["id"]
            },
            callback: {
                beforeClick: function (treeId, treeNode) {
                    $("#parentId").val(treeNode.id);
                    $("#parentName").val(treeNode.name);
                }
            }
        };


        $('#parentName').bind('click', function () {
            MyShop.modalalert("<ul id='treeDemo' class='ztree'></ul>");
            $.fn.zTree.init($("#treeDemo"), setting);
        })
    }

    var handlerDropzoneFile = function (dropId, pictureId) {
        $("div#"+dropId).dropzone({
            url: "/upload/dropzone/file",
            // 文件大小，单位：MB
            maxFilesize: 1,
            maxFiles: 1,// 一次性上传的文件数量上限
            dictMaxFilesExceeded: "您最多只能上传1个文件！",

            init: function () {
                this.on("success", function (file, data) {
                    $("#"+pictureId).val(data.imaginUrl)
                });
            }
        });
    }


    var handlerShowUEditor =function (id) {
        var ue = UE.getEditor('container');


        ue.ready(function() {
            ue.setContent($('#'+id).val());
        });

        ue.addListener("contentChange",function () {
            $("#"+id).val(ue.getContent());
        })
    }
    /**
     * 共有方法
     */
    return {
        /**
         * 初始化icheck
         */
        initicheck:function () {
            handlerInitICheck();
        },

        /**
         * 初始化datatables
         */
        initdatatable:function (url,columns) {
             handlerInitDataTable(url,columns)
        },

        /**
         * 提示模态框
         */
        modalalert:function(message){
            handlerModalAlert(message)
        },

        /**
         * 确认模态框
         */
        modalconfirm:function(message,callback){
           handlerModalConfirm(message,callback)
        },

        /**
         * 删除用户信息
         */
        showdelete:function (url, message) {
             handlerShowDelete(url, message)
        },

        /**
         * 修改用户信息
         */
        showupdate:function (url) {
           handlerShowUpdate(url)
        },

        /**
         * 批量删除用户信息
         */
        showdeletemulti:function (url, message) {
           hanglerShowDeleteMulti(url, message)
        },

        /**
         * 展示单个下信息
         * @param url
         */
        showview: function (url) {
           handlerShowView(url)
        },

        /**
         * ZTree展示树形结构
         * @param url
         */
        showzTree: function (url) {
            handlerShowZTree(url)
        },

        /**
         * Dropzone上传图片
         * @param dropId
         * @param pictureId
         */
        initDropzoneFile: function (dropId, pictureId) {
          handlerDropzoneFile(dropId, pictureId)
        },

        /**
         * UEditor富文本编辑器
         * @param id
         */
        showUEditor: function (id) {
            handlerShowUEditor(id)
        },
    }
}();

//MyShop初始化器
$(document).ready(function () {
    MyShop.initicheck();
})