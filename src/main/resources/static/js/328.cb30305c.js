"use strict";(self["webpackChunksimida_web"]=self["webpackChunksimida_web"]||[]).push([[328],{3328:function(e,a,l){l.r(a),l.d(a,{default:function(){return b}});l(560);var t=l(3396),o=l(4870),i=l(7139),s=l(9143),d=l(3417),r=l(2261),n=l(4476),u=l(4471),p=l(7625),m=l(1120);const c=e=>((0,t.dD)("data-v-07325228"),e=e(),(0,t.Cn)(),e),v={class:"content"},f=c((()=>(0,t._)("div",{style:{fontSize:"24px",paddingTop:"20px",paddingBottom:"30px",display:"flex",justifyContent:"center"}}," 修改密码 ",-1))),w=c((()=>(0,t._)("div",{style:{fontSize:"20px",paddingTop:"20px",paddingBottom:"30px",display:"flex",justifyContent:"center",color:"#de1c31"}}," 通过邮箱验证身份 ",-1))),g={style:{display:"flex",justifyContent:"center",marginTop:"30px"}};var x={__name:"editPasswd",setup(e){const a=(0,o.iH)({email:"",password:"",verificationCode:""}),l=(0,o.iH)(null);(0,t.bv)((()=>{a.value.email=p.h.userInfo.email}));const c=(e,a,l)=>{n.Z[e]({message:a,description:l,duration:2})},x=(0,o.iH)(0),y=(0,o.iH)({text:"获取验证码",disabled:!0}),_=()=>{u.Z.post("/mail/sendVerificationCodeHtmlMail",{to:a.value.email,subject:"身份校验验证码",title:"邮箱验证",msg:"修改密码"}).then((e=>{1001===e.data.code?(c("success",e.data.msg),y.value.disabled=!0,y.value.text="",x.value=Date.now()+6e4):c("warning",e.data.msg)}))},b=()=>{x.value=0,y.value.text="重新获取",y.value.disabled=!1},h=(0,o.qj)({password:[{required:!0,message:"密码不能为空",trigger:"blur"}],email:[{required:!0,message:"邮箱不能为空",trigger:"blur"},{type:"email",message:"请输入正确的邮箱地址",trigger:"blur"}],verificationCode:[{required:!0,message:"验证码不能为空",trigger:"blur"}]}),C=()=>{""!==a.value.email&&(y.value.disabled=!1)},k=async e=>{e&&await e.validate((async(e,a)=>{e&&U()}))},U=()=>{u.Z.put("/user/changePassword",{password:a.value.password,verificationCode:a.value.verificationCode},{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((e=>{4001===e.data.code?(c("success","修改成功，请重新登录！"),localStorage.removeItem("userInfo"),localStorage.removeItem("token"),localStorage.removeItem("uid"),localStorage.removeItem("tour"),p.h.userInfo={},p.h.isLogin=!1,p.h.active="recommended",m.Z.push("/recommended")):c("error",e.data.msg)}))};return(e,n)=>{const u=(0,t.up)("font-awesome-icon"),p=(0,t.up)("el-form-item"),m=(0,t.up)("el-countdown"),c=(0,t.up)("a-button");return(0,t.wg)(),(0,t.iD)("div",v,[f,w,(0,t._)("div",null,[(0,t.Wm)((0,o.SU)(s.ly),{ref_key:"passwdFormRef",ref:l,model:a.value,rules:h,style:{width:"550px",margin:"20px auto"}},{default:(0,t.w5)((()=>[(0,t.Wm)(p,{prop:"email"},{default:(0,t.w5)((()=>[(0,t.Wm)((0,o.SU)(d.EZ),{type:"email",modelValue:a.value.email,"onUpdate:modelValue":n[0]||(n[0]=e=>a.value.email=e),class:"input-with-select",clearable:"",onBlur:C,disabled:""},{prepend:(0,t.w5)((()=>[(0,t.Uk)(" 邮       箱 ")])),prefix:(0,t.w5)((()=>[(0,t.Wm)(u,{icon:"fa-solid fa-envelope"})])),_:1},8,["modelValue"])])),_:1}),(0,t.Wm)(p,{prop:"verificationCode"},{default:(0,t.w5)((()=>[(0,t.Wm)((0,o.SU)(d.EZ),{modelValue:a.value.verificationCode,"onUpdate:modelValue":n[1]||(n[1]=e=>a.value.verificationCode=e),class:"input-with-select"},{prepend:(0,t.w5)((()=>[(0,t.Uk)(" 验  证  码 ")])),prefix:(0,t.w5)((()=>[(0,t.Wm)(u,{icon:"fa-solid fa-envelope-open-text"})])),suffix:(0,t.w5)((()=>[(0,t.Wm)((0,o.SU)(r.ElButton),{type:"primary",onClick:_,style:{"min-width":"90px","max-width":"90px"}},{default:(0,t.w5)((()=>[(0,t.Uk)((0,i.zw)(y.value.text)+" ",1),0!==x.value?((0,t.wg)(),(0,t.j4)(m,{key:0,"value-style":"color: #fff",value:x.value,format:"ss",onFinish:b},null,8,["value"])):(0,t.kq)("",!0)])),_:1})])),_:1},8,["modelValue"])])),_:1}),(0,t.Wm)(p,{prop:"password"},{default:(0,t.w5)((()=>[(0,t.Wm)((0,o.SU)(d.EZ),{type:"password",modelValue:a.value.password,"onUpdate:modelValue":n[2]||(n[2]=e=>a.value.password=e),class:"input-with-select",clearable:""},{prepend:(0,t.w5)((()=>[(0,t.Uk)(" 修改密码 ")])),prefix:(0,t.w5)((()=>[(0,t.Wm)(u,{icon:"fa-solid fa-lock"})])),_:1},8,["modelValue"])])),_:1})])),_:1},8,["model","rules"]),(0,t._)("div",g,[(0,t.Wm)(c,{type:"primary",class:"exam-btn",onClick:n[3]||(n[3]=e=>k(l.value))},{default:(0,t.w5)((()=>[(0,t.Uk)(" 提交 ")])),_:1})])])])}}},y=l(89);const _=(0,y.Z)(x,[["__scopeId","data-v-07325228"]]);var b=_}}]);
//# sourceMappingURL=328.cb30305c.js.map