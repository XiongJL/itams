#创建视图
创建此视图后才可使用权限相关功能  
`
CREATE VIEW view_roles
as
select u.uid,u.uname,u.PERSONID,u.pwd,r.name,r.workshop
                    from ITAMS_User_Role ur 
                    LEFT JOIN ITAMS_User u on u.uid= ur.uid
                    LEFT JOIN ITAMS_Role r on ur.rid= r.rid
go
`
