"use client";

import { Button } from "@/components/ui/button";
import { signOut } from "next-auth/react";

export function SignOutClient() {
    return <Button onClick={() => signOut()}>로그아웃</Button>;
}
