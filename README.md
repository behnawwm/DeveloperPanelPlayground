## Project Description:
#### Developer Panel Showcase for Android Applications

This Android project serves as a comprehensive demonstration of implementing a developer panel in an Android application, specifically designed to be accessible only in debug builds. The developer panel provides essential tools and features for developers during the application development phase.

## Summary:
- Build-Type Based Accessibility: The developer panel is accessible only in debug builds, ensuring that it doesn't interfere with the release version of your application.

- Multiple Access Points:

  - Notification Access: Access the developer panel through a prominent notification, providing quick access to essential tools.

  - Floating Icon: A draggable floating icon allows developers to access the panel from anywhere in the application by dragging it to different positions on the screen.

  - Drawer on Main Screen: Integrate a modal drawer directly on the main screen for easy access to developer tools without leaving the current context.

  - Separate Launcher Application: Demonstrate the use of a separate launcher application to access the developer panel, showcasing an alternative method for convenience.

- Gradle Configuration: Leverage Gradle build configurations to conditionally include or exclude the developer panel based on the build type.

- Dependency Injection (DI): Showcase best practices for dependency injection to maintain a clean and modular codebase.

- Comprehensive Example: This repository provides a fully functional example, demonstrating the integration of the developer panel using a variety of access points, ensuring a well-rounded understanding of the implementation.

Explore the codebase, follow the step-by-step guide, and leverage the provided examples to enhance your Android application development workflow with a developer panel tailored for debugging and testing purposes.


### Screenshots
Launcher Icon:

![IMAGE 2023-11-16 19:16:13](https://github.com/behnawwm/DeveloperPanelPlayground/assets/61078796/6a8974e0-0a68-4200-b868-5eabb0509763)

Floating Icon:

<img src="https://github.com/behnawwm/DeveloperPanelPlayground/assets/61078796/08fa1ab8-c68a-4902-a7f4-9bf3bc960b94" width="40%" >

Drawer:

<img src="https://github.com/behnawwm/DeveloperPanelPlayground/assets/61078796/cfe70e23-a294-4f0e-bd38-f317dc401272" width="40%" >

Notification:

![IMAGE 2023-11-16 19:17:12](https://github.com/behnawwm/DeveloperPanelPlayground/assets/61078796/be6bb605-5284-43c7-ac10-3ea664e043c9)

