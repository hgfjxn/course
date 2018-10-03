# coding:utf-8
from message.api import MessageService;
from thrift.protocol import TBinaryProtocol
from thrift.transport import TTransport
from thrift.transport import TSocket
from thrift.server import TServer

import smtplib
from email.mime.text import MIMEText
from email.header import Header

import logging
import sys

sender = ""
authCode = ""
smtpServer = ""

logging.basicConfig(level=logging.INFO,
                    stream=sys.stdout,
                    format='%(asctime)s - %(filename)s[line:%(lineno)d] - %(levelname)s: %(message)s')
log = logging.getLogger(__name__)


class MessageHandler:
    def sendMobileMessage(self, mobile, content):
        """
        Parameters:
         - mobile: mobile number to send
         - content: message content to send
        """

        log.info(msg="send message to %s: %s" % (mobile, content))
        return True

    def sendEmailMessage(self, email, content):
        """
        Parameters:
         - email: email address to send
         - content: message content to send
        """
        log.info("send message to %s: %s" % (email, content))
        mail = MIMEText(content, "plain", "utf-8")
        mail["From"] = sender
        mail["To"] = email
        mail["Subject"] = Header("课程系统邮件", "utf-8")
        try:
            smtp = smtplib.SMTP(smtplib)
            smtp.login(sender, authCode)
            smtp.sendmail(sender, [email], mail.as_string())
        except smtplib.SMTPException:
            log.error("smtplib exception")
            return False
        return True


if __name__ == '__main__':
    listen = 9090
    handler = MessageHandler()
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket('127.0.0.1', listen)
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, transportFactory, protocolFactory)

    log.info(msg="server starting at localhost %s" % listen)

    server.serve()

    log.info("message server stoped")
