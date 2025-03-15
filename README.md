# install mySQL workbanch set password and put hear 

spring.datasource.url=jdbc:mysql://localhost:3306/product_management
spring.datasource.username=root
spring.datasource.password=
#mySQL workbench
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

#this configuration line is for sms sending
#create twilio account and put details
twilio.account_sid=
twilio.auth_id=
twilio.trial_number=

#this configuration line is for whatsapp message sending
twilio.whatsapp.trial_number=whatsapp:

#this configuration line is for mail sending 
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


#Admin Details
admin.name=
admin.email=
admin.phone_number=
admin.whatsapp_number=whatsapp:

#Product Threshold

product.threshold_value=15
