ironloggui
============

This *highly experimental* project is a GUI for collecting log output in one single GUI.
Its primarily designed to work with irondemo, but you can use it to display all logs you want.

This project was created by the [Trust@HsH research group][trustathsh]
at the [Hochschule Hannover][hshannover] (University of Applied Sciences and Arts,
Hannover, Germany).

Configuration
=============

The configuration parameters can be done through the
`ironloggui.yml` file in the ironloggui package.
Look at config file for further informations


Building
========
You can build ironloggui by executing:

    $ mvn package

in the root directory of the ironloggui project.
Maven should download all further needed dependencies for you. After a successful
build you should find the `ironloggui-x.x.x-bundle.zip` in the `target` sub-directory.

Running
=======
To run the binary package of irongenlog simply execute:

    $ ./start.sh


Feedback
========
If you have any questions about this project or developing with [ifmapj][ifmapj] in general, feel
free to contact us via <trust@f4-i.fh-hannover.de>


Note
====

ironloggui is an experimental prototype and is not suitable for actual use.

Feel free to fork/contribute.

[ifmapj]: https://github.com/trustathsh/ifmapj.git
[trustathsh]: http://trust.f4.hs-hannover.de
[hshannover]: https://www.hs-hannover.de/
