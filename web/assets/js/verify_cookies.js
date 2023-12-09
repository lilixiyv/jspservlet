// 自定义的验证函数
    document.addEventListener("DOMContentLoaded", function() {
    // 在这里读取 Cookie
        let accountCookie = getCookie("account");

        if (!accountCookie) {
    // 如果 Cookie 不存在，执行相应的操作，比如跳转到登录页面
    alert("请先登录！");
    window.location.href = "login.jsp";
} else {
    // 如果 Cookie 存在，可以继续页面的其他操作
    console.log("用户已登录，账号为: " + accountCookie);
}
});

    // 用于获取指定名称的 Cookie 的辅助函数
    function getCookie(name) {
    let cookieArr = document.cookie.split("; ");
    for (let i = 0; i < cookieArr.length; i++) {
    let cookiePair = cookieArr[i].split("=");
    if (cookiePair[0] === name) {
    return cookiePair[1];
}
}
    return null;
}
