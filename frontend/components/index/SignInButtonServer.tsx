import { getServerSession } from "next-auth";
import { SignOutClient } from "./SignOutClient";
import { SignInClient } from "./SignInClient";

import Image from "next/image";
import Link from "next/link";

export async function SignInButtonServer() {
    const session = await getServerSession();

    if (session) {
        return (
            <>
                <Link href={`/dashboard`}>
                    <Image
                        src={session.user?.image ?? "/logo512.png"}
                        width={32}
                        height={32}
                        alt="Your Name"
                    />
                </Link>
                <SignOutClient />
            </>
        );
    }

    return <SignInClient />;
}
