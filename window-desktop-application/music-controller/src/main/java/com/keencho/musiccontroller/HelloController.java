package com.keencho.musiccontroller;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.event.KeyEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        WinDef.HWND hwnd = null;
        while ( true ) {
            hwnd = User32.INSTANCE.FindWindowEx(null, hwnd, null, null);

            if (hwnd == null) {
                break;
            }

            var buffer = new char[1024];
            User32.INSTANCE.GetWindowText(hwnd, buffer, buffer.length);
            var title = Native.toString(buffer);

            if (title.toUpperCase().contains("YouTube Music".toUpperCase())) {

                User32.INSTANCE.ShowWindow(hwnd, 9);

                User32.INSTANCE.PostMessage(hwnd, WinUser.WM_KEYDOWN, new WinDef.WPARAM(0x20), new WinDef.LPARAM(0));
//                User32.INSTANCE.PostMessage(hwnd, WinUser.WM_KEYDOWN, new WinDef.WPARAM(0x20), new WinDef.LPARAM(1));

//                var input = new WinUser.INPUT();
//                input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
//                input.input.ki.wVk = new WinDef.WORD(KeyEvent.VK_SPACE);
//                input.input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_EXTENDEDKEY);
//
//                var res = User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[] { input }, input.size());
//
//                System.out.println(res);

                break;
            }
        }

        welcomeText.setText("Welcome to JavaFX Application!");
    }
}