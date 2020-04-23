<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Requested Login Credentials</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td align="center" bgcolor="maroon" style="padding: 40px 0 30px 0;">
                <img src="https://www.europe.umgc.edu/sites/default/files/field/image/1_19-MKT-168-coming-soon-banner_0.jpg" alt="UMGC City Zoning Admin Poral " style="margin:auto;width:600;height:163;" />
            </td>
        </tr>
        <tr>
            <td bgcolor="white" style="padding: 40px 30px 40px 30px;">
                <p>Dear ${name},</p>
              <p>Here are the user login credentials that you requested to gain access to the <a href="${host}/home">UMGC City Zoning Admin Portal</a>.</p>
              <p><b>Username:</b> ${emailAddress}</p>
              <p><b>Password:</b> ${password}</p>
              <br>
                <p>Thanks</p>
              <p><i>~UMGC City Zoning Application Team</i>

              </p>
            </td>
        </tr>
        <tr>
            <td bgcolor="gold" style="padding: 30px 30px 30px 30px;font-size: 12px">
              <p>&copy; 2020, UMGC City Team 1</p>
              <p>This free HTML code generator tool was created by a team of IT software engineering graduate students from the University of Maryland Global Campus (UMGC).</p>
            </td>
        </tr>
    </table>
</body>
</html>
