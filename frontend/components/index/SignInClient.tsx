"use client";

import { Button } from "@/components/ui/button";
import { signIn } from "next-auth/react";

export function SignInClient() {
    return (
        <Button
            onClick={() =>
                signIn("google", {
                    callbackUrl: "http://localhost:3000/dashboard",
                })
            }
        >
            로그인
        </Button>
    );
}
