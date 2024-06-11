import React from "react";
import Image from "next/image";
import ToggleTheme from "@/components/ui/ToggleTheme";
// import { Button } from "@/components/ui/button";
// import { SignInButton, SignOutButton } from "@/components/index/SignInButton";
// import { SignInButtonServer } from "./SignInButtonServer";
import LoginButton from "./LoginButton";

export default function Header() {
    return (
        <nav className=" mx-auto max-w-7xl sm:px-12 mt-6">
            <div className="flex items-center  justify-between">
                <div className="flex items-center gap-1">
                    <Image
                        src="/logo.png"
                        alt="Logo"
                        width={100}
                        height={24}
                        priority
                    />
                    <h1 className="hidden sm:block text-xl sm:text-2xl font-bold">
                        {process.env.PRODUCT_NAME}
                    </h1>
                </div>

                <div className="flex gap-2">
                    <ToggleTheme />
                </div>
            </div>
        </nav>
    );
}
