# team-template
Project team template

At some point, you'll need to replace this file to describe your project, how to get it running, and that sort of thing.

Meanwhile, here are some tips to get started.

## using the repository

1. Everyone on the team must clone this project repository to their computers
3. Remember to always create and check out a branch before changing files
2. Create issues here in GitHub! Lots of issues! Anything you do for the project could be an issue...
    * Create an issue because you need to decide what the software is going to be.
    * Create an issue because you need to learn how to use the programming language/framework.
    * Create an issue because you need to write the Software Requirements Specification
    * And, of course, create issues because you want to add features or fix bugs.
3. Every issue is a discussion. Participate!
4. Refer to the issues in which you've participated in your stand-up quiz so we know what you've been doing for the project even if it isn't code merged into master.
5. A Project lets you organize issues on a progress board to see what's on hold, being worked on, done. You can have multiple Project boards within a repository. Maybe create one for documentation, or artwork if your project needs that.

Tip: If you are using one of JetBrains's IDEs (such as PyCharm or IntelliJ), open the <Application>/Preferences (macOS) or File/Settings (Windows) for your project, select Version Control / Git, and turn ***on*** the checkbox for Use Credential Helper.

## Files to change in this template repo
Probably all of them!

**.gitignore**: I created this at https://gitignore.io/ with the settings macos,windows,linux,jetbrains,eclipse,visualstudiocode,java,c++,python. You might need more. You might want less.

**LICENSE**: This is the Creative Commons 0 (public domain) license which may not be compatible with third-party code you might include in your project. Probably isn't. So figure out what's appropriate and change it.

**README.md** That's this file. Once everyone is set up, replace it with a description of your project. Once your project runs, include instructions for installing and running it!

# using a framework's setup tool
If you're using a framework or IDE that wants to help you get started by creating your project folder (e.g., LibGDX), it's going to be a little more difficult to get it set up as the initial state of your project. (Not every framework needs a particular file structure. If you're using tkinter, for example, you don't need to do this.)

One person on the team should do this initial setup which will then be shared by the whole team. 

Start by cloning your team's repository (maybe you already did) and then creating and checking out a new branch. A good name would be, for example, `libgdx-setup` (if you're setting up libgdx) or `venv` if you're setting up a Python virtual environment using that tool.

It's easiest if the tool you're using will do the setup directly on your cloned project repository, but some will require an empty folder. Go ahead and create a folder for the setup tool if it needs one. 

Do whatever is needed for setup. If setup used a separate folder, copy the new files it made into your project repository (using Finder/Windows Explorer/etc). Make sure to include any hidden folders or files (Seeing them is an option in the View menu in Windows; on macOS, press command-shift-period [dot].)  

Check that the setup works. E.g., run a LibGDX project to see their logo appear.

From the project repository, `git add *` to track all the new files, then `git commit -m "initial setup"` and `git push` to the remote (and if you get an error that the branch doesn't exist, copy/paste the fix it gives you). In GitHub's web page, create a pull request to merge to main.

If you can, ask for reviews from people using different operating systems or microprocessors.

The reviewer should then do a ``git fetch`` to update their repo relative to the origin, then the review can  check it out (``git checkout <branchname>``). If it works for the reviewer, too, then the PR can be approved. If not, say what happened in the PR discussion and figure out how to fix it. Once it works for at least one other person, it probably makes sense to merge it into master.
