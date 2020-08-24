# Team-Project-Mirror
Restaurant Management System. Mirror of the source code for my second-year Team Project course. This was built in February/March 2018 in a group of 7, using Git as our version control system and implementing proper software development practices such as Scrum.

## Project Introduction
This project was designed to be integrated into a restaurant to replace the conventional workflow. Each table would have a tablet with it which the customer would use to browse the menu and place their order. A waiter would then get a notification to come over and confirm the order with the customer.

The order is then sent to the kitchen where they can see what has been ordered, any comments the customer might have left (e.g. no lettuce) and then will make the order. Once they have done that the kitchen staff will click done which will alert the waiter to come and deliver the meal. At the end of the meal the customer can then use the Stripe integration to pay the bill. 

## Tech Stack
The back end was built using a [Java Spark](http://sparkjava.com/) which is a micro web framework. This provided the appropriate api end points to interface with our database which was handled by [Hibernate ORM](https://hibernate.org/orm/). Dependencies were handled by [Maven](https://maven.apache.org/).

The front end was a web application created with HTML, CSS and Javascript. We used JQuery to give Javascript more functionality to manipulate the web page and the [Bootstrap CSS and Javascript library](https://getbootstrap.com/) to give the site a clean functional feel. We used the [Stripe API](https://stripe.com/gb) to provide in app payments for the customer.

## What we learned
This was one of our first experiences building an application of this scale, especially using proper software engineering techniques. Although we were all familiar with Java, HTML, CSS and Javascript, we had never combined them in this way. We also learned a lot about dependency tools such as Maven. We found out the importance and usefulness of frontend frameworks such as VueJS or ReactJS for handling highly interactive web applications which we wish we would have used. Additionally we learned Git best practices and how to handle situations such as merge conflicts.

## Screenshots
