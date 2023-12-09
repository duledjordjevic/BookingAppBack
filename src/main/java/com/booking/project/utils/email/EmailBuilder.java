package com.booking.project.utils.email;

public class EmailBuilder {
    public String buildEmail(String name, String link) {
        return "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n" +
                "  <title> Welcome to Booking </title>\n" +
                "  <!--[if !mso]><!-- -->\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "  <!--<![endif]-->\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <style type=\"text/css\">\n" +
                "    #outlook a {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      -ms-text-size-adjust: 100%;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    td {\n" +
                "      border-collapse: collapse;\n" +
                "      mso-table-lspace: 0pt;\n" +
                "      mso-table-rspace: 0pt;\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "      border: 0;\n" +
                "      height: auto;\n" +
                "      line-height: 100%;\n" +
                "      outline: none;\n" +
                "      text-decoration: none;\n" +
                "      -ms-interpolation-mode: bicubic;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      display: block;\n" +
                "      margin: 13px 0;\n" +
                "    }\n" +
                "  </style>\n" +
                "  <!--[if mso]>\n" +
                "        <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "          <o:AllowPNG/>\n" +
                "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "        </xml>\n" +
                "        <![endif]-->\n" +
                "  <!--[if lte mso 11]>\n" +
                "        <style type=\"text/css\">\n" +
                "          .mj-outlook-group-fix { width:100% !important; }\n" +
                "        </style>\n" +
                "        <![endif]-->\n" +
                "  <!--[if !mso]><!-->\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Quattrocento:wght@400;700&amp;display=swap\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "  <style type=\"text/css\">\n" +
                "    @import url(https://fonts.googleapis.com/css2?family=Quattrocento:wght@400;700&amp;display=swap);\n" +
                "  </style>\n" +
                "  <!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (min-width:480px) {\n" +
                "      .mj-column-per-100 {\n" +
                "        width: 100% !important;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (max-width:480px) {\n" +
                "      table.mj-full-width-mobile {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      td.mj-full-width-mobile {\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "  <style type=\"text/css\">\n" +
                "    a,\n" +
                "    span,\n" +
                "    td,\n" +
                "    th {\n" +
                "      -webkit-font-smoothing: antialiased !important;\n" +
                "      -moz-osx-font-smoothing: grayscale !important;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color:#ADD8E6;\">\n" +
                "  <div style=\"display:none;font-size:1px;color:#ffffff;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;\"> Preview - Welcome to Booking " + name+ " </div>\n" +
                "  <div style=\"background-color:#ADD8E6;\">\n" +
                "    <!--[if mso | IE]>\n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
                "              <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "              <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "            <tr>\n" +
                "              <td\n" +
                "                 class=\"\" width=\"600px\"\n" +
                "              >\n" +
                "          \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "              <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:8px 8px 0 0;max-width:600px;\">\n" +
                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#ffffff;background-color:#ffffff;width:100%;border-radius:8px 8px 0 0;\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
                "                        <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:middle;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:middle;width:100%;\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:middle;\" width=\"100%\">\n" +
                "                            <tbody><tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "                                  <tbody>\n" +
                "                                    <tr>\n" +
                "                                      <td style=\"width:150px;\">\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </tbody>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <p style=\"border-top:solid 4px #f9f9f9;font-size:1px;margin:0px auto;width:100%;\">\n" +
                "                                </p>\n" +
                "                                <!--[if mso | IE]>\n" +
                "        <table\n" +
                "           align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:solid 4px #f9f9f9;font-size:1px;margin:0px auto;width:550px;\" role=\"presentation\" width=\"550px\"\n" +
                "        >\n" +
                "          <tr>\n" +
                "            <td style=\"height:0;line-height:0;\">\n" +
                "              &nbsp;\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      <![endif]-->\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody></table>\n" +
                "                        </div>\n" +
                "                        <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td\n" +
                "                 class=\"\" width=\"600px\"\n" +
                "              >\n" +
                "          \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "              <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:0 0 8px 8px;max-width:600px;\">\n" +
                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#ffffff;background-color:#ffffff;width:100%;border-radius:0 0 8px 8px;\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-top:0px;text-align:center;\">\n" +
                "                        <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                            <tbody><tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "                                  <tbody>\n" +
                "                                    <tr>\n" +
                "                                      <td style=\"width:550px;\">\n" +
                "                                        <img alt=\"welcome image\" height=\"auto\" src=\"https://i.postimg.cc/NjyMsSFq/booking-high-resolution-logo.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:14px;\" width=\"550\" />\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </tbody>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"right\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:14px;font-weight:400;line-height:24px;text-align:right;color:#000000;\">\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#000000;\">\n" +
                "                                  <h1 style=\"margin: 0; font-size: 32px; line-height: 40px; font-weight: 700;\">Welcome to Booking!</h1>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#000000;\">You're almost ready to get going, we just need to verify your email before starting. <a href=\""+ link +"\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\">Visit this link</a> in your browser to confirm your address.</div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#000000;\">Or click on the button below to complete your registration and get started!</div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" vertical-align=\"middle\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
                "                                  <tbody><tr>\n" +
                "                                    <td align=\"center\" bgcolor=\"#428dfc\" role=\"presentation\" style=\"border:none;border-radius:3px;cursor:auto;mso-padding-alt:10px 25px;background:#428dfc;\" valign=\"middle\">\n" +
                "                                      <a href=\"" + link + "\" style=\"display: inline-block; background: #428dfc; color: #ffffff; font-family: Quattrocento; font-size: 14px; font-weight: bold; line-height: 30px; margin: 0; text-decoration: none; text-transform: uppercase; padding: 10px 25px; mso-padding-alt: 0px; border-radius: 3px;\" target=\"_blank\"> Complete registration </a>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody></table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:16px;font-weight:400;line-height:24px;text-align:center;color:#333333;\">Have questions or need help? Email us at <a href=\"#\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\"> \n" +
                "bookingwebsite0@gmail.com </a></div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody></table>\n" +
                "                        </div>\n" +
                "                        <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td\n" +
                "                 class=\"\" width=\"600px\"\n" +
                "              >\n" +
                "          \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "              <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "                        <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                            <tbody><tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:16px;font-weight:400;line-height:24px;text-align:center;color:#333333;\"><a class=\"footer-link\" href=\"#\" style=\"text-decoration: none; color: #000; font-weight: 400;\">Support</a>   |   <a class=\"footer-link\" href=\"#\" style=\"text-decoration: none; color: #000; font-weight: 400;\">Forums</a>   |  <a class=\"footer-link\" href=\"#\" style=\"text-decoration: none; color: #000; font-weight: 400;\">Contact</a>   |  <a class=\"footer-link\" href=\"#\" style=\"text-decoration: none; color: #000; font-weight: 400;\">Log In</a></div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:16px;font-weight:400;line-height:24px;text-align:center;color:#333333;\">123 Medalling Jr., Suite 100, Parrot Park, CA 12345<br /> © 2020 [Booking] Inc.</div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <!--[if mso | IE]>\n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "      \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                                  <tbody><tr>\n" +
                "                                    <td style=\"padding:4px;\">\n" +
                "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:24px;\">\n" +
                "                                        <tbody><tr>\n" +
                "                                          <td style=\"font-size:0;height:24px;vertical-align:middle;width:24px;\">\n" +
                "                                            <a href=\"#\" target=\"_blank\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\">\n" +
                "                                              <img alt=\"twitter-logo\" height=\"24\" src=\"https://codedmails.com/images/social/black/twitter-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"24\" />\n" +
                "                                            </a>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody></table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody></table>\n" +
                "                                <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                                  <tbody><tr>\n" +
                "                                    <td style=\"padding:4px;\">\n" +
                "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:24px;\">\n" +
                "                                        <tbody><tr>\n" +
                "                                          <td style=\"font-size:0;height:24px;vertical-align:middle;width:24px;\">\n" +
                "                                            <a href=\"#\" target=\"_blank\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\">\n" +
                "                                              <img alt=\"facebook-logo\" height=\"24\" src=\"https://codedmails.com/images/social/black/facebook-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"24\" />\n" +
                "                                            </a>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody></table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody></table>\n" +
                "                                <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                                  <tbody><tr>\n" +
                "                                    <td style=\"padding:4px;\">\n" +
                "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:24px;\">\n" +
                "                                        <tbody><tr>\n" +
                "                                          <td style=\"font-size:0;height:24px;vertical-align:middle;width:24px;\">\n" +
                "                                            <a href=\"#\" target=\"_blank\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\">\n" +
                "                                              <img alt=\"instagram-logo\" height=\"24\" src=\"https://codedmails.com/images/social/black/instagram-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"24\" />\n" +
                "                                            </a>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody></table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody></table>\n" +
                "                                <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                                  <tbody><tr>\n" +
                "                                    <td style=\"padding:4px;\">\n" +
                "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:24px;\">\n" +
                "                                        <tbody><tr>\n" +
                "                                          <td style=\"font-size:0;height:24px;vertical-align:middle;width:24px;\">\n" +
                "                                            <a href=\"#\" target=\"_blank\" style=\"color: #428dfc; text-decoration: none; font-weight: bold;\">\n" +
                "                                              <img alt=\"dribbble-logo\" height=\"24\" src=\"https://codedmails.com/images/social/black/linkedin-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"24\" />\n" +
                "                                            </a>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody></table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody></table>\n" +
                "                                <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      <![endif]-->\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                                <div style=\"font-family:Quattrocento;font-size:16px;font-weight:400;line-height:24px;text-align:center;color:#333333;\">Update your <a class=\"footer-link\" href=\"https://google.com\" style=\"text-decoration: none; color: #000; font-weight: 400;\">email preferences</a> to choose the types of emails you receive, or you can <a class=\"footer-link\" href=\"https://google.com\" style=\"text-decoration: none; color: #000; font-weight: 400;\"> unsubscribe </a>from all future emails.</div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody></table>\n" +
                "                        </div>\n" +
                "                        <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td\n" +
                "                 class=\"\" width=\"600px\"\n" +
                "              >\n" +
                "          \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "              <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "                        <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                            <tbody><tr>\n" +
                "                              <td style=\"font-size:0px;word-break:break-word;\">\n" +
                "                                <!--[if mso | IE]>\n" +
                "    \n" +
                "        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"1\" style=\"vertical-align:top;height:1px;\">\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "                                <div style=\"height:1px;\">   </div>\n" +
                "                                <!--[if mso | IE]>\n" +
                "    \n" +
                "        </td></tr></table>\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody></table>\n" +
                "                        </div>\n" +
                "                        <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "              <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      <![endif]-->\n" +
                "  </div>\n" +
                "\n" +
                "\n" +
                "</body></html>";
    }
}
