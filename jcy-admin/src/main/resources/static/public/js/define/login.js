var vm = new Vue({
    el: '#div-login',
    data: {
        username: '',
        password: '',
        captcha: '',
        src: 'captcha.jpg'
    },
    beforeCreate: function () {
        //判断当前的窗口是不是顶层窗口
        if (self != top) {
            //嵌套了，设置顶层窗口就是当前窗口
            top.location.href = self.location.href;
        }
    },
    methods: {
        refreshCode: function () {
            this.src = "captcha.jpg?t=" + $.now();
        },
        login: function (event) {
            if (!$('#uName').val()) {
                fn_tips('用户名不能为空', '#uName', 3);
                return false;
            }
            if (!$('#uPwd').val()) {
                fn_tips('密码不能为空', '#uPwd', 3);
                return false;
            }
            if (!$('#uCaptcha').val()) {
                fn_tips('验证码不能为空', '#uCaptcha', 3);
                return false;
            }

            var data = "username=" + vm.username + "&password=" + vm.password + "&captcha=" + vm.captcha;
            $.post('sys/login', data, function (result) {
                if (result.code == 0) {//登录成功
                    parent.location.href = 'index.html';
                } else if(result.code == 10001){
                    fn_tips(result.msg, '#uName', 3);
                    vm.refreshCode();
                } else if (result.code == 10003) {
                    fn_tips(result.msg, '#uCaptcha', 3);
                    vm.refreshCode();
                } else if(result.code == 10002){
                    fn_tips(result.msg, '#uPwd', 3);
                    vm.refreshCode();
                } else if(result.code == 30001){
                    layer.alert(result.msg);
                    vm.refreshCode();
                }
            }, "json");
        }
    }
});